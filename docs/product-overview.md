# Product Overview – Event Management & Ticketing System

## 1. Purpose

This system is designed to support a large event company in managing events, ticket sales, and seat allocation efficiently and safely.

The goal is to provide a reliable platform for administrators to manage events and for customers to purchase tickets with seat selection and secure payment.

---

## 2. Problem Statement

The company needs a centralized system that:

- Manages events, sessions, and venues
- Handles different ticket types and availability
- Supports seat selection
- Processes payments
- Prevents conflicts such as double-booked seats

---

## 3. Target Users

- **Administrator**
  - Manages events, sessions, rooms, and ticket types

- **Customer**
  - Purchases tickets
  - Selects seats
  - Makes payments

---

## 4. Core Features

### Event Management
- Create and manage events
- Define event duration (start/end)
- Associate multiple sessions to an event

### Session Management
- Define date and time for each session
- Associate a session with a room

### Venue Management
- Create and manage rooms
- Define seats within each room

### Ticket Management
- Create and manage ticket types (e.g., full, half-price, senior)
- Control ticket quantity per session
- Associate ticket types with sessions

### Seat Selection
- Allow customers to select specific seats per session
- Ensure seats belong to the selected room

### Payment
- Process payments via credit card

---

## 5. Key Constraints & Rules

- An event must have one or more sessions
- A session must be associated with exactly one room
- Each room contains multiple seats
- Ticket availability must be controlled per session
- A seat can only be assigned to one customer per session
- The system must prevent double booking of seats
- Ticket purchases must be linked to a valid payment

---

## 6. Success Criteria

- No double-booking of seats under concurrent access
- Accurate ticket availability per session
- Reliable payment processing
- Clear and manageable administrative workflows

---

## 7. Scope

### In Scope
- Event, session, and room management
- Ticket type and availability control
- Seat selection
- Credit card payments

### Out of Scope (initially)
- External integrations beyond payment
- Advanced analytics/reporting
- Dynamic pricing

---

## 8. Assumptions

- Payments are handled via credit card only (initial version)
- Each session occurs in a single room
- Seat selection is mandatory for ticket purchase
- High concurrency is expected during ticket sales

---