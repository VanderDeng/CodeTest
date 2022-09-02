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
import axios from '../common/axios';
import Map from '../components/Map';

function Zombie() {
  const mapRef = useRef();

  const errorMsg = {
    nullValue: 'You have to input all parameters',
    dataDup: 'Creatures position are duplicated',
    exceed: 'The coordinate value cannot exceed the dimension',
  };

  const [gridSize, setGridSize] = React.useState('');
  const [zombieX, setZombieX] = React.useState('');
  const [zombieY, setZombieY] = React.useState('');
  const [allValues, setAllValues] = React.useState({
    commands: '',
  });
  const [creatureList, setList] = React.useState([{ x: '', y: '' }]);
  const [visibleData, setVisible] = React.useState({
    isVisible: false,
    message: errorMsg.nullValue,
  });

  const commChange = event => {
    let value = allValues.commands;
    if (
      event.nativeEvent.data === 'U' ||
      event.nativeEvent.data === 'D' ||
      event.nativeEvent.data === 'R' ||
      event.nativeEvent.data === 'L'
    ) {
      setAllValues({
        ...allValues,
        commands: value + event.nativeEvent.data,
      });
    } else if (event.nativeEvent.data === null) {
      setAllValues({
        ...allValues,
        commands: value.substring(0, value.length - 1),
      });
    }
  };

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
      axios.post('/zombie', params).then(res => {
        if (res.name !== 'AxiosError') {
          mapRef.current.setMap(gridSize, res.data);
        } else {
          setVisible({
            isVisible: true,
            message: res.message,
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
        message: errorMsg.nullValue,
      });
      return false;
    } else if (checkDup(params.creatures, params.zombie)) {
      setVisible({
        isVisible: true,
        message: errorMsg.dataDup,
      });
      return false;
    } else if (checkMax(params.creatures, params.zombie)) {
      setVisible({
        isVisible: true,
        message: errorMsg.exceed,
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

  function checkDup(cArr, zArr) {
    const keys = ['x', 'y'];
    const filtered = cArr.concat(zArr).filter(
      (
        s => o =>
          (k => !s.has(k) && s.add(k))(keys.map(k => o[k]).join('|'))
      )(new Set())
    );
    return filtered.length !== cArr.concat(zArr).length;
  }

  function checkMax(cArr, zArr) {
    return cArr
      .concat(zArr)
      .map(item => {
        return item.x >= gridSize || item.y >= gridSize;
      })
      .includes(true);
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
              min={1}
              isRequired={true}
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
                min={0}
                max={gridSize - 1}
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
                min={0}
                max={gridSize - 1}
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
                    min={0}
                    max={gridSize - 1}
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
                    min={0}
                    max={gridSize - 1}
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
            <FormLabel>Movement List(Must be U,D,R,L)</FormLabel>
            <Input
              value={allValues.commands}
              name="commands"
              onChange={commChange}
              placeholder="Input a list of moves the zombies will make"
            />
          </FormControl>
        </Grid>
        <Button colorScheme="teal" onClick={submitOpt}>
          Submit
        </Button>

        <div style={{ marginTop: '20px' }}>
          <Container maxW="container.sm">
            <Map ref={mapRef} />
          </Container>
        </div>
      </Box>
    </ChakraProvider>
  );
}

export default Zombie;