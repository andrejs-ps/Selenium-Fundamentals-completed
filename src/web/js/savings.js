
(() => {
  'use strict'
  const periodYields = new Map([
    [0.5, 0.04],
    [1, 0.05],
    [2, 0.06],
  ]);

  let input = document.getElementById('deposit');
  let period = document.getElementById('period');
  let result = document.getElementById('result');

  input.addEventListener('input', event => {
    displayReturn();
  });

  period.addEventListener('change', event => {
    displayReturn();
  });

  function displayReturn() {
    let sum = input.value;
    if (sum < 1) { return; }
    let selected = period.options[period.selectedIndex].value;
    let periodInYears = convertPeriod(selected);
    let expectedReturn = calculateReturn(sum, periodInYears);
    let roundedReturn = parseFloat(expectedReturn).toFixed(2);
    result.classList.add("alert");
    result.innerText = `After ${selected} you will earn $${roundedReturn} on your deposit`;
  }

  function convertPeriod(option) {
    let periodKeys = Array.from(periodYields.keys());
    switch (option) {
      case '6 months':
        return periodKeys[0];
      case '1 Year':
        return periodKeys[1];
      case '2 Years':
        return periodKeys[2];
    }
  }

  function calculateReturn(sum, periodInYears) {
    let yearlyYield = periodYields.get(periodInYears);
    return sum * periodInYears * yearlyYield;
  }


})()
