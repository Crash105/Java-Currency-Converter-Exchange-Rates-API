# Currency Converter with Exchange Rates API

[![License](https://img.shields.io/badge/license-MIT-blue.svg)](https://opensource.org/licenses/MIT)

Currency Converter is a Java application that allows you to convert amounts between different currencies using the Exchange Rates API.

## Table of Contents
- [Overview](#overview)
- [Requirements](#requirements)
- [Installation](#installation)
- [Usage](#usage)
- [License](#license)

## Overview

Currency Converter is a simple Java application that leverages the Exchange Rates API to provide accurate and up-to-date currency conversion rates. The application prompts the user to enter an amount, the currency code to convert from, and the currency code to convert to. It then fetches the exchange rates from the API, performs the conversion, and displays the result.

## Requirements

Before running the Currency Converter, ensure you have the following prerequisites:

1. Java Development Kit (JDK) 8 or higher.
2. Internet connection to access the Exchange Rates API.
3. An API key from [Exchange Rates API](https://exchangeratesapi.io/) (sign up for a free account to obtain the API key).

## Installation

1. Clone this repository to your local machine.
2. Create a file named `.env` in the root directory of the project.
3. Add your API key to the `.env` file in the following format: ```API_KEY=YOUR_API_KEY_HERE```
4. Save the `.env` file.

## Usage

To run the Currency Converter, use the following steps:

1. Open a terminal or command prompt.
2. Navigate to the root directory of the project.
3. Run the following command to compile the Java classes: ```javac CurrencyConverter.java```
4. Run the application: ```java CurrencyConverter```
5. Follow the on-screen instructions to perform currency conversions.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE)
