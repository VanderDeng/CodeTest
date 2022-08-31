import React from 'react';
import {
  ChakraProvider,
  Box,
  Grid,
  theme,
  FormControl,
  FormLabel,
  Input,
  Button,
} from '@chakra-ui/react';
import { ColorModeSwitcher } from './ColorModeSwitcher';
import axios from 'axios';

function App() {
  const [allValues, setAllValues] = React.useState({
    gridSize: '',
    zombie: '',
    creatures: '',
    commands: '',
  });
  const handleChange = event =>
    setAllValues({ ...allValues, [event.target.name]: event.target.value });

  const service = axios.create({
    baseURL: 'http://localhost:8080',
    timeout: 1000,
    headers: { 'Access-Control-Allow-Origin': '*' },
    withCredentials: false,
  });

  function submitOpt() {
    service.get('/getPosition').then(res => {
      console.log(res);
    });
    console.log(allValues);
  }

  return (
    <ChakraProvider theme={theme}>
      <Box textAlign="center" fontSize="xl">
        <Grid minH="20vh" p={100}>
          <ColorModeSwitcher justifySelf="flex-end" />
          <FormControl isRequired>
            <FormLabel>Dimensions of the grid</FormLabel>
            <Input
              name="gridSize"
              onChange={handleChange}
              placeholder="Input dimensions of the grid"
            />
            <FormLabel>Zombie Initial position</FormLabel>
            <Input
              name="zombie"
              onChange={handleChange}
              placeholder="Input the initial position of the zombie"
            />
            <FormLabel>Creatures Initial position</FormLabel>
            <Input
              name="creatures"
              onChange={handleChange}
              placeholder="Input a list of initial positions of the creatures"
            />
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
    </ChakraProvider>
  );
}

export default App;
