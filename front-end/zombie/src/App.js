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
  NumberInput,
  NumberInputField,
  NumberInputStepper,
  NumberIncrementStepper,
  NumberDecrementStepper,
} from '@chakra-ui/react';
import axios from 'axios';
import Map from './Map';

function App() {
  const [gridSize, setGridSize] = React.useState('0');
  const [zombie, setZombie] = React.useState('0');
  const [creatures, setCreatures] = React.useState('0');
  const [allValues, setAllValues] = React.useState({
    commands: '',
  });
  const [display, setDisplay] = React.useState('');
  const handleChange = event =>
    setAllValues({ ...allValues, [event.target.name]: event.target.value });
  const childRef = useRef();

  const service = axios.create({
    baseURL: 'http://localhost:8080',
    timeout: 1000,
    headers: { 'Access-Control-Allow-Origin': '*' },
    withCredentials: false,
  });

  function submitOpt() {
    let params = {
      gridSize: gridSize,
      zombie: zombie,
      creatures: creatures,
      ...allValues,
    };
    console.log(params);
    childRef.current.setMap(gridSize);
    service.get('/test').then(res => setDisplay(res.data));
  }

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
          <FormControl isRequired>
            <FormLabel>Dimensions of the grid</FormLabel>
            <NumberInput
              onChange={valueString => setGridSize(valueString)}
              value={gridSize}
            >
              <NumberInputField />
              <NumberInputStepper>
                <NumberIncrementStepper />
                <NumberDecrementStepper />
              </NumberInputStepper>
            </NumberInput>
            <FormLabel>Zombie Initial position</FormLabel>
            <NumberInput
              onChange={valueString => setZombie(valueString)}
              value={zombie}
            >
              <NumberInputField />
              <NumberInputStepper>
                <NumberIncrementStepper />
                <NumberDecrementStepper />
              </NumberInputStepper>
            </NumberInput>
            <FormLabel>Creatures Initial position</FormLabel>
            <NumberInput
              onChange={valueString => setCreatures(valueString)}
              value={creatures}
            >
              <NumberInputField />
              <NumberInputStepper>
                <NumberIncrementStepper />
                <NumberDecrementStepper />
              </NumberInputStepper>
            </NumberInput>
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
