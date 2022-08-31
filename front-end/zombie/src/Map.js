import React, {
  useState,
  useEffect,
  forwardRef,
  useImperativeHandle,
} from 'react';
import { Heading } from '@chakra-ui/react';
import './index.css';

const Map = forwardRef((props, ref) => {
  useImperativeHandle(ref, () => ({
    setMap(num) {
      setDimension(num);
    },
  }));

  const [dimension, setDimension] = useState(0);
  const [position, setPosition] = useState([]);

  const block = {
    width: '100px',
    height: '100px',
    backgroundColor: 'white',
    border: '0.5px solid',
    margin: 'auto',
    lineHeight: '100px',
  };
  const grid = {
    width: 100 * dimension,
    display: 'flex',
    flexWrap: 'wrap',
    marginTop: '20px',
    boxShadow: `0px 10px 10px rgba(0,0,0,0.1)`,
  };

  const makeMap = () => {
    let arr = [];
    for (let i = 0; i < dimension; i++) {
      let temp = [];
      for (let j = 0; j < dimension; j++) {
        temp.push(
          <div style={block} key={j}>
            {i},{j}
          </div>
        );
      }
      arr.push(temp);
    }

    setPosition(arr);
  };

  useEffect(() => {
    makeMap();
  }, [dimension]);

  return (
    <div className="gridMap">
      <div>
        <Heading as="h3" size="md" textAlign="center">
          Position Map
        </Heading>
      </div>
      <div className="mapContainer">
        <div style={grid}>{position}</div>
      </div>
    </div>
  );
});

export default Map;
