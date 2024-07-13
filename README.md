# Smart Transit Ticketing System

A cloud-native, microservices-based smart ticketing web application using Java, ReactJS, and MySQL. This project includes distributed transaction handling, real-time analytics, and integration of NFC technology for contactless payments.

## Features

- User registration and management
- Ticket booking
- Payment processing
- Real-time event handling with Kafka
- Simple and responsive frontend built with ReactJS

## Architecture

The application consists of the following microservices:

1. **User Service**: Manages user registration and authentication.
2. **Ticket Service**: Handles ticket booking and management.
3. **Payment Service**: Processes payments.
4. **Consumer Service**: Listens to Kafka topics for real-time event handling.

## Prerequisites

- Java 17
- Maven
- Node.js and npm
- MySQL
- Kafka and Zookeeper

## Getting Started

### Clone the Repository

```sh
git clone https://github.com/sahilagrawal15/Smart-Ticketing.git
cd Smart-Ticketing
