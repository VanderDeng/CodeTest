import React, { useRef } from 'react';
import {
  ChakraProvider,
  Heading,
  Box,
  Grid,
  Container,
  theme,
  FormControl,
  FormLabel,
  Input,
  Button,
  Stack,
  NumberInput,
  NumberInputField,
  NumberInputStepper,
  NumberIncrementStepper,
  NumberDecrementStepper,
  Alert,
  AlertIcon,
  AlertTitle,
} from '@chakra-ui/react';
import axios from 'axios';
import Map from './components/Map';

function App() {
  const [gridSize, setGridSize] = React.useState('');
  const [zombieX, setZombieX] = React.useState('');
  const [zombieY, setZombieY] = React.useState('');
  const [allValues, setAllValues] = React.useState({
    commands: '',
  });
  const [creatureList, setList] = React.useState([{ x: '', y: '' }]);

  const handleChange = event =>
    setAllValues({ ...allValues, [event.target.name]: event.target.value });
  const childRef = useRef();
  const [visibleData, setVisible] = React.useState({
    isVisible: false,
    message: 'You have to input all parameters',
  });

  const data = {
    zombies: [
      { x: '1', y: '2' },
      { x: '2', y: '3' },
      { x: '3', y: '4' },
    ],
    creatures: [
      { x: '1', y: '1' },
      { x: '2', y: '2' },
    ],
  };
  //   const [display, setDisplay] = React.useState('');

  const service = axios.create({
    baseURL: 'http://localhost:8080',
    timeout: 1000,
    headers: { 'Access-Control-Allow-Origin': '*' },
    withCredentials: false,
  });

  function submitOpt() {
    let params = {
      gridSize: gridSize,
      zombie: {
        x: zombieX,
        y: zombieY,
      },
      creatures: creatureList,
      ...allValues,
    };

    if (checkValid(params)) {
      childRef.current.setMap(gridSize, data);
      service.post('/createPosition', params).then(res => {
        if (
          !Object.prototype.isPrototypeOf(res) &&
          Object.keys(res).length === 0
        ) {
          //   setDisplay(res.data);
        } else {
          setVisible({
            isVisible: true,
            message: 'response data error',
          });
        }
      });
    }
  }

  function checkValid(params) {
    if (
      !params.gridSize ||
      !params.commands ||
      params.zombie.x === '' ||
      params.zombie.y === '' ||
      params.creatures
        .map(element => {
          return element.x !== '' && element.y !== '';
        })
        .includes(false)
    ) {
      setVisible({
        isVisible: true,
        message: 'You have to input all parameters',
      });
      return false;
    } else if (checkDup(params.creatures)) {
      setVisible({
        isVisible: true,
        message: 'Creatures position are duplicated',
      });
      return false;
    } else {
      setVisible({
        ...visibleData,
        isVisible: false,
      });
      return true;
    }
  }

  function checkDup(arr) {
    const xList = arr.map(value => value.x);
    const xSet = new Set(xList);
    const yList = arr.map(value => value.y);
    const ySet = new Set(yList);
    if (xSet.size !== xList.length && ySet.size !== yList.length) {
      return true;
    } else {
      return false;
    }
  }

  React.useEffect(() => {
    setList(creatureList);
  }, [creatureList]);

  return (
    <ChakraProvider theme={theme}>
      <Heading as="h2" textAlign="center" padding={5}>
        Zombie Apocalypse
      </Heading>
      <Box textAlign="center" fontSize="xl">
        <Grid
          minH="20vh"
          marginLeft={300}
          paddingRight={300}
          paddingBottom={10}
        >
          {visibleData.isVisible ? (
            <Alert status="error">
              <AlertIcon />
              <AlertTitle>{visibleData.message}</AlertTitle>
            </Alert>
          ) : (
            ''
          )}
          <FormControl isRequired>
            {/* Dimensions */}
            <FormLabel>Dimensions of the grid</FormLabel>
            <NumberInput
              onChange={valueString => setGridSize(valueString)}
              value={gridSize}
              marginBottom={5}
            >
              <NumberInputField />
              <NumberInputStepper>
                <NumberIncrementStepper />
                <NumberDecrementStepper />
              </NumberInputStepper>
            </NumberInput>
            {/* Zombie */}
            <FormLabel>Zombie Initial position (X, Y)</FormLabel>
            <Stack shouldWrapChildren direction="row">
              <NumberInput
                onChange={valueString => setZombieX(valueString)}
                value={zombieX}
                marginBottom={5}
              >
                <NumberInputField />
                <NumberInputStepper>
                  <NumberIncrementStepper />
                  <NumberDecrementStepper />
                </NumberInputStepper>
              </NumberInput>
              <NumberInput
                onChange={valueString => setZombieY(valueString)}
                value={zombieY}
                marginBottom={5}
              >
                <NumberInputField />
                <NumberInputStepper>
                  <NumberIncrementStepper />
                  <NumberDecrementStepper />
                </NumberInputStepper>
              </NumberInput>
            </Stack>
            {/* Creatures */}
            <FormLabel>Creatures Initial position (X, Y)</FormLabel>
            {(creatureList || []).map((item, index) => {
              const { x, y } = item;
              return (
                <Stack shouldWrapChildren direction="row" key={index}>
                  <NumberInput
                    value={x}
                    marginBottom={5}
                    onChange={valueAsString => {
                      const _creatureList = [...creatureList];
                      _creatureList[index] = {
                        ..._creatureList[index],
                        x: valueAsString,
                      };
                      setList(_creatureList);
                    }}
                  >
                    <NumberInputField />
                    <NumberInputStepper>
                      <NumberIncrementStepper />
                      <NumberDecrementStepper />
                    </NumberInputStepper>
                  </NumberInput>
                  <NumberInput
                    value={y}
                    marginBottom={5}
                    onChange={valueAsString => {
                      const _creatureList = [...creatureList];
                      _creatureList[index] = {
                        ..._creatureList[index],
                        y: valueAsString,
                      };
                      setList(_creatureList);
                    }}
                  >
                    <NumberInputField />
                    <NumberInputStepper>
                      <NumberIncrementStepper />
                      <NumberDecrementStepper />
                    </NumberInputStepper>
                  </NumberInput>
                  <Button
                    onClick={() => {
                      const _creatureList = [...creatureList];
                      _creatureList.splice(index + 1, 0, { x: '', y: '' });
                      setList(_creatureList);
                    }}
                  >
                    +
                  </Button>
                  <Button
                    style={{
                      display: creatureList.length === 1 ? 'none' : 'inline',
                    }}
                    onClick={() => {
                      if (creatureList.length !== 1) {
                        const _creatureList = [...creatureList];
                        _creatureList.splice(index, 1);
                        setList(_creatureList);
                      }
                    }}
                  >
                    -
                  </Button>
                </Stack>
              );
            })}
            {/* Movement */}
            <FormLabel>Movement List</FormLabel>
            <Input
              name="commands"
              onChange={handleChange}
              placeholder="Input a list of moves the zombies will make"
            />
          </FormControl>
        </Grid>
        <Button colorScheme="teal" onClick={submitOpt}>
          Submit
        </Button>

        <div style={{ marginTop: '20px' }}>
          <Container maxW="container.sm">
            <Map ref={childRef} />
          </Container>
        </div>
      </Box>
    </ChakraProvider>
  );
}

export default App;
