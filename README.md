# StorageManager 1.0 App

## Contents

1. [Description](#Description)
2. [Sample Flow](#Sample-Flow)
3. [Other Features](#Other-Features)
4. [Installation Guide](#Installation-Guide)
5. [Working Example](#Working-Example)
6. [Further Improvements](#Further-Improvements)
7. [License](#License)

## Description

### **Task:** Develop a CLI App for managing a luggage storage facility

### **Required Functionalities:**

- 1 luggage takes 1 spot
- the hours are rounded upwards
- get info about vacancy
- store a luggage
- retrieve a luggage

### **Delivered Properties:**

- all the ones required above
- get info about storage facility
- controlled access to luggages
- admin mode
- custom exception handling
- Javadoc documenting

## Sample-Flow

The user runs the app and is shown the following Option Menu:

```console
--- Option Menu (Type the corresponding number) ---

1. Get Storage Info
2. Add Luggage
3. Get Luggage Info
4. Retrieve Luggage
5. Access Admin Mode
6. Exit StorageManager
```

### Storage Info

By typing `1`, the user is shown a similar output:

```console
-- Storage Facility Info --

- Current vacancy: 100
- First Hour Price: 10
- Subsequent Hours Price: 5

Note: The time is rounded upwards! (e.g. 61 mins -> 2h)
```

### Add Luggage

By typing `2`, the user is prompted for his last name:

```console
-- Luggage Deposit --

- Please provide your last name:
>
```

After typing it, he is given a unique access code:

```console
-- Luggage Deposit --

- Please provide your last name:
> Amarandei

Your unique code is: AMARANDEI#1

Note: Don't lose it as you may not be able to retrieve your luggage!
```

### Get Luggage Info

By typing `3`, the user is prompted again, but this time, for an access code:

```console
-- Luggage Management --

- Please provide your access code:
```

By typing the correct code, the user is now able to get info about their luggage:

```console
-- Luggage #1 --

- Owner: Amarandei
- Deposited at: 29-07-2022 14:52

- Accumulated Cost: 10
```

### Retrieve Luggage

By typing `4`, the user will enter a similar flow, the only difference being that, at the end, the baggage is removed from storage:

```console
-- Luggage Management --

- Please provide your access code:
> AMARANDEI#1

-- Luggage #1 --

- Owner: Amarandei
- Deposited at: 29-07-2022 14:52

- Accumulated Cost: 10

Luggage Retrieved!
```

### Admin Mode

By typing `5`, the user will enter a flow similar to the one currently presented, but only if the right access code is provided:

```console
-- Admin Option Menu --

1. Modify Number of Storage Units
2. Modify Pricing
3. Exit Admin Mode
```

The capabilities of the Admin are self-explanatory.

### Exit Storage Manager

By typing `6`, the user exits the app:

```console
----  Bye, Bye! ----
```

## Installation Guide

### **Requirements:** Java 8, Maven 3.8.6

**Note:** _The guide is written having MacOS Monterey as a reference_

For a detailed guide on how to install Java, head over to [this site](https://docs.oracle.com/javase/10/install/installation-jdk-and-jre-macos.htm).

For installing Maven (given that `homebrew` is also installed on your machine), just type:

```console
brew install maven
```

Other than that, just clone the repository and you are ready to go!

## Further Improvements

- Secure Authentication
- Persistence
- Multi-Threading Support

## License

[The Unlicense](LICENSE)
