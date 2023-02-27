(() => {
  'use strict'

  navigator.geolocation.getCurrentPosition(success, error);

  function success(position) {
    const crd = position.coords;
    const location = document.getElementById('location');
    const sameLat = (crd.latitude - london.latitude) < 0.2
    const sameLong = (crd.longitude - london.longitude) < 0.2

    if (sameLat && sameLong) {
      location.innerText = `You are visiting us from London`;
    } else {
      const zone = Intl.DateTimeFormat().resolvedOptions().timeZone;
      location.innerText = `You are visiting us from ${zone}`;
    }
  }

  function error(err) {
    console.warn(`ERROR(${err.code}): ${err.message}`);
  }

  const london = {
    latitude: 51.508773530774434,
    longitude: -0.11121168632860247
  }
})()
