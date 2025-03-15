# CloudReach Airlines Web Application

This project is my submission for the CGI Summer Internship, as part of the requirements for the internship application.

## Project description
CloudReach Airlines is a web application designed to provide users with an easy way to search for and book flights. The app allows users to search for flights to various destinations, set flight types (departure or arrival), and choose dates. Additionally, users can select the number of flights they want to view.

The user can navigate between three pages:
1. Main page
2. Search flight page
3. Contact page

## 1. Main page
The main page serves as the landing page for users, showcasing the best flight offers from CloudReach Airlines. The page includes the following key features:

Users can filter flights by:
- Destination: Enter a destination to search for available flights.
- Price: Set a maximum price to only display flights within the specified budget.
- Sort by: Sort flights by price, date, or flight duration.
- Sort order: Toggle between ascending and descending sort order.

Once the filters are applied, the matching flights are displayed in a table with the following columns:
- Destination
- Departure location
- Price (EUR)
- Date
- Duration
- Airline

Users can click the "Book" button to proceed to the booking page for the selected flight.

If no flights match the applied filters, a message is displayed suggesting users try again later or modify their filter options.

This page dynamically loads flight data from the database.

### 1.1 Booking page
The booking page allows users to view detailed information about the selected flight and proceed with booking it. The page is designed to provide a smooth flow for users to select their preferences and enter passenger details before finalizing the booking.

Key features of the booking page include:
- **Flight details**: Displays information such as the destination, departure location, price, date, duration, and airline.
- **Passenger information**: Users can input the number of adults and children for the booking. By default, one adult and zero children are pre-filled.
- **Seat preferences**: The page allows users to specify seat preferences. These preferences include:
  - Window seat: Users can indicate they prefer a window seat, if available.
  - Middle seat: Users can indicate they prefer a middle seat, if available.
  - Aisle seat: Users can indicate they prefer an aisle seat, if available.
  - Extra legroom: Users can indicate they prefer extra legroom on their seat, if available.

These preferences allow users to customize their booking experience and select the options that best fit their needs.

The page provides buttons to either go back to the main page or proceed to the next step (e.g., seat selection).

The data for this page is dynamically loaded from the database using the flight ID passed through the route. Once the details are shown, users can modify their passenger details and preferences before proceeding with the booking.

### 1.2 Seats page
The seat selection page allows users to view available seats for their selected flight and choose their preferred seats based on availability and personal preferences.

Key features of the seat selection page include:

- **Passenger summary**: The page displays the number of adults and children for the booking. It also shows the seat preferences (if any) that the user has selected, such as:
  - Window seat
  - Middle seat
  - Aisle seat
  - Extra legroom
- **Seat availability**: Available seats are displayed in a grid. Each seat is represented as a button, and the following conditions apply:
  - **Booked seats**: Seats that are already booked are marked and cannot be selected.
  - **Suggested seats**: If the user has selected seat preferences (e.g., window seat, extra legroom), these seats are visually highlighted as suggested seats.
  - **Selectable seats**: Seats that are available for selection are interactive, and the user can click to select or deselect them.
- **Seat selection**: The user can select up to the number of seats based on the number of adults and children in the booking. If the limit is reached, no more seats can be selected.
- **Reset selection**: Users can clear their seat selections at any time.
- **Navigation buttons**:
  - **Back button**: Returns the user to the home page.
  - **Book button**: Finalizes the seat selection and proceeds with booking the seats.

The page fetches seat data dynamically from the backend based on the flight ID passed through the URL. The seat information includes the seat status (available or booked), seat type (e.g., window, aisle), and any extra features (e.g., extra legroom).

## 2. Search flight page
This page is part of the CloudReach Airlines web application, where users can search and browse flight tickets by entering various search criteria. The page retrieves flight data via an external API to provide real-time flight information.

### Features:
1. **Flight search**:
  - Users can select a departure location (e.g., Tallinn, Riga, Helsinki, etc.).
  - Users can choose whether they are searching for departures or arrivals.
  - Users can enter the desired flight date, with a minimum date set to 8 days from today.
  - Users can specify the number of flights they wish to see (from 1 to 100).
  
2. **Displaying flights**:
  - Once the search criteria are submitted, flights are retrieved from the AviationStack API and displayed in a table format.
  - Information such as airline name, aircraft model, departure and arrival gates, destination airport, and booking options are shown.
  
3. **Booking alert**:
  - Each flight listing has a "Book" button that, when clicked, shows an alert informing the user to contact CloudReach Airlines via email for booking.

## 3. Contact page
The contact page provides users with the necessary contact details for CloudReach Airlines. It includes the following information: phone number, email address, and physical address.

Additionally, the page features an embedded map showing the exact location of our office, making it easy for users to find us.

Users can view our contact information and easily navigate to our physical location using the interactive map. The page serves as a direct communication channel for users seeking assistance or further details about our services.
