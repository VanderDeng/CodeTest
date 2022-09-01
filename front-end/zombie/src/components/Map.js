import React, {
  useState,
  useEffect,
  forwardRef,
  useImperativeHandle,
} from 'react';
import { Heading } from '@chakra-ui/react';
import './index.css';
import myZombie from '../assets/zombie.svg';
import myPirate from '../assets/pirate.svg';

const Map = forwardRef((props, ref) => {
  useImperativeHandle(ref, () => ({
    setMap(num, mapData) {
      setDimension(num);
      format(mapData);
    },
  }));

  const [dimension, setDimension] = useState(0);
  const [position, setPosition] = useState([]);
  const [zombies, setZombie] = useState({});
  const [creatures, setCreature] = useState({});

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
  const zombie = {
    width: '100px',
    height: '100px',
    backgroundImage: `url(${myZombie})`,
    border: '0.5px solid',
    margin: 'auto',
    lineHeight: '100px',
  };
  const creature = {
    width: '100px',
    height: '100px',
    backgroundImage: `url(${myPirate})`,
    border: '0.5px solid',
    margin: 'auto',
    lineHeight: '100px',
  };

  function format(data) {
    const zombieList = data.zombies;
    const creatureList = data.creatures;
    setZombie(zombieList);
    setCreature(creatureList);
  }

  const makeMap = () => {
    let arr = [];
    for (let i = 0; i < dimension; i++) {
      let temp = [];
      for (let j = 0; j < dimension; j++) {
        if (
          zombies
            .map(item => {
              if (item.x === i.toString() && item.y === j.toString()) {
                return true;
              } else {
                return false;
              }
            })
            .includes(true)
        ) {
          temp.push(<div style={zombie} key={i + ',' + j}></div>);
        } else if (
          creatures
            .map(item => {
              if (item.x === i.toString() && item.y === j.toString()) {
                return true;
              } else {
                return false;
              }
            })
            .includes(true)
        ) {
          temp.push(<div style={creature} key={i + ',' + j}></div>);
        } else {
          temp.push(<div style={block} key={i + ',' + j}></div>);
        }
      }
      arr.push(temp);
    }

    setPosition(arr);
  };

  useEffect(() => {
    makeMap(dimension);
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
