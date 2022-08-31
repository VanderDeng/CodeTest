import React from 'react';
import {
  ChakraProvider,
  Box,
  Text,
  Link,
  VStack,
  Code,
  Grid,
  theme,
  FormControl,
  FormLabel,
  FormErrorMessage,
  FormHelperText,
  Input,
} from '@chakra-ui/react';
import { ColorModeSwitcher } from './ColorModeSwitcher';
import { Logo } from './Logo';

function App() {
  return (
    <ChakraProvider theme={theme}>
      <Box textAlign="center" fontSize="xl">
        <Grid minH="100vh" p={100}>
          <ColorModeSwitcher justifySelf="flex-end" />
          <FormControl isRequired>
            <FormLabel>Dimensions of the grid</FormLabel>
            <Input placeholder="Input dimensions of the grid" />
          </FormControl>
        </Grid>
      </Box>
    </ChakraProvider>
  );
}

export default App;
