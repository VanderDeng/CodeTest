import React from 'react';
import {
  ChakraProvider,
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
import Board from './Board';

function App() {
  const [gridSize, setGridSize] = React.useState('0');
  const [zombie, setZombie] = React.useState('0');
  const [creatures, setCreatures] = React.useState('0');
  const [allValues, setAllValues] = React.useState({
    commands: '',
  });
  const [display, setDisplay] = React.useState('');
  const [border, setBorder] = React.useState(Array(20).fill(null));
  const handleChange = event =>
    setAllValues({ ...allValues, [event.target.name]: event.target.value });

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

    service.get('/test').then(res => setDisplay(res.data));
  }

  return (
    <ChakraProvider theme={theme}>
      <Box textAlign="center" fontSize="xl">
        <Grid
          minH="20vh"
          marginLeft={300}
          paddingRight={300}
          paddingTop={100}
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
      </Box>
      <div style={{ marginTop: '20px' }}>
        <Container maxW="container.sm">
          Display:{display}
          <Board />
        </Container>
      </div>
    </ChakraProvider>
  );
}

export default App;
