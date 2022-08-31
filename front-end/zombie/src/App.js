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

function App() {
  return (
    <ChakraProvider theme={theme}>
      <Box textAlign="center" fontSize="xl">
        <Grid minH="20vh" p={100}>
          <ColorModeSwitcher justifySelf="flex-end" />
          <FormControl isRequired>
            <FormLabel>Dimensions of the grid</FormLabel>
            <Input placeholder="Input dimensions of the grid" />
            <FormLabel>Zombie Initial position</FormLabel>
            <Input placeholder="Input the initial position of the zombie" />
            <FormLabel>Creatures Initial position</FormLabel>
            <Input placeholder="Input a list of initial positions of the creatures" />
            <FormLabel>Movement List</FormLabel>
            <Input placeholder="Input a list of moves the zombies will make" />
          </FormControl>
        </Grid>
        <Button
          colorScheme="teal"
          onClick={() => {
            console.log('!23');
          }}
        >
          Submit
        </Button>
      </Box>
    </ChakraProvider>
  );
}

export default App;
