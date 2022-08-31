import React, { useState, useEffect } from 'react';
import './index.css';

const Board = () => {
  const [dimension, setDimension] = useState(0);
  const [position, setPosition] = useState([]);

  const black = {
    width: '100px',
    height: '100px',
    backgroundColor: 'white',
    border: 'solid',
  };
  //   const white = {
  //     width: '100px',
  //     height: '100px',
  //     backgroundColor: 'white',
  //   };
  const grid = {
    width: 100 * dimension,
    display: 'flex',
    flexWrap: 'wrap',
    marginTop: '20px',
    boxShadow: `0px 10px 10px rgba(0,0,0,0.1)`,
    // border: 'solid',
  };

  const makeMap = () => {
    let arr = [];
    for (let i = 0; i < dimension; i++) {
      let temp = [];
      for (let j = 0; j < dimension; j++) {
        temp.push(
          <div style={black}>
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
        <h2>Position Map</h2>

        <input
          type="number"
          placeholder="Enter the Dimension"
          onChange={e => setDimension(e.target.value)}
        />
      </div>
      <div className="mapContainer">
        <div style={grid}>{position}</div>
      </div>
    </div>
  );
};

export default Board;
