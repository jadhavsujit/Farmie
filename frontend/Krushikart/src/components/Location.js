import React, { useState } from 'react'
import '../css/location.css'
import Navbars from './Navbars'

function Location () {
  const [locationText, setLocationText] = useState('')

  const handleClick = () => {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
        position => {
          setLocationText(
            `Latitude: ${position.coords.latitude}, Longitude: ${position.coords.longitude}`
          )
        },
        error => {
          setLocationText('Error getting location: ' + error.message)
        }
      )
    } else {
      setLocationText('The browser does not support geolocation')
    }
  }
  return (
    <div class='location'>
      <h1 id='title'>FARMIE</h1>
      <p id='para1'>Food from people you know, delivered to you</p>
      <p>
        <button
          type='button'
          className='btn btn-success btn-lg'
          id='get-location'
          onClick={handleClick}
        >
          Find Your Local Farmer
        </button>
      </p>
      <div id='location-details' style={{color:'white'}}>{locationText}</div>
      
    </div>
  )
}

export default Location;