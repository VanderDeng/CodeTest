import React, { useState, forwardRef, useImperativeHandle } from 'react';
import { Heading } from '@chakra-ui/react';
import './index.css';
import myZombie from '../assets/zombie.svg';
import myPirate from '../assets/pirate.svg';

const Map = forwardRef((props, ref) => {
  useImperativeHandle(ref, () => ({
    setMap(num, mapData) {
      setDimension(num);
      createMap(num, mapData);
    },
  }));

  const [dimension, setDimension] = useState(0);
  const [position, setPosition] = useState([]);

  const emptyBlock = {
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
  const zombieStyle = {
    width: '100px',
    height: '100px',
    backgroundImage: `url(${myZombie})`,
    border: '0.5px solid',
    margin: 'auto',
    lineHeight: '100px',
  };
  const creatureStyle = {
    width: '100px',
    height: '100px',
    backgroundImage: `url(${myPirate})`,
    border: '0.5px solid',
    margin: 'auto',
    lineHeight: '100px',
  };

  const createMap = (size, mapData) => {
    let arr = [];
    for (let i = 0; i < size; i++) {
      let temp = [];
      for (let j = 0; j < size; j++) {
        const matchPosition = ({ x, y }) => x === i && y === j;
        if (mapData.zombies.some(matchPosition)) {
          temp.push(<div style={zombieStyle} key={i + ',' + j}></div>);
        } else if (mapData.creatures.some(matchPosition)) {
          temp.push(<div style={creatureStyle} key={i + ',' + j}></div>);
        } else {
          temp.push(<div style={emptyBlock} key={i + ',' + j}></div>);
        }
      }
      arr.push(temp);
    }

    setPosition(arr);
  };

  return (
    <div className="gridMap">
      <div>
        {dimension !== 0 ? (
          <Heading as="h3" size="md" textAlign="center">
            Final Positions
          </Heading>
        ) : (
          ''
        )}
      </div>
      <div className="mapContainer">
        <div style={grid}>{position}</div>
      </div>
    </div>
  );
});

export default Map;
