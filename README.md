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

## Project Structure
The CloudReach Airlines web application is divided into two main parts: the front-end and the back-end.
### 1. Frontend
The frontend is a Vue.js application.
1. **Components:**
Contains the components for the application.
* Footer is responsible for displaying the footer of the application.
* Header is responsible for displaying the header of the application.
* EmailSubcription manages the newsletter subscription, allowing users to enter and validate their email, display success or error messages, and subscribe for updates on offers and flights.

2. **Views:**
Contains the pages for the application.
* Book is responsible for displaying the booking page with flight details and passenger information.
* Contact displays the contact information and office location.
* FindFlights allows users to search for flights based on criteria.
* Home displays the main page with flight offers and filters.
* SeatSelection is responsible for displaying the seat selection page for the chosen flight.

3. **Router:**
Defines the application's routes, maps each route to a specific component.
4. **App.vue:**
The layout is set up by importing and rendering the Header, EmailSubscription, and Footer components, with the main content dynamically displayed through router-view.

### 2. Backend
The backend is a Spring Boot application written in Java.

1. **Controllers:**
   * ContactController: Responsible for handling requests to the /contact endpoint and rendering the contact.html page, displaying the contact information to the user.
   * Handles the flight-related requests such as fetching available flights, saving and deleting flight data, retrieving seats for a specific flight, and integrating with both the database and external APIs to manage flight information.
   * SeatController: Manages seat-related requests, including retrieving seats for a specific flight, adding new seats, updating the status of a seat, and fetching seat details by ID.
2. **Models:**
   * Flight: Represents a flight entity with attributes. It is mapped to the flights table in the database and contains methods for getting and setting flight details.
   * Seat: Represents a seat entity with attributes. It is linked to the Flight entity through a many-to-one relationship, and is mapped to the seats table in the database. This model contains methods for getting and setting seat details.
    
3. **Repositories:** Contains the custom exceptions for the application.

4. **Services:** Contains the services for the application.
   * FlightService: Provides business logic for handling flight-related operations.
   * SeatService: Handles the business logic for seat-related operations.
     
**DataLoader:** This class is responsible for loading initial data into the database when the application starts. It deletes existing entries and populates the Flight and Seat tables with sample data (flights and seats with random availability and types).

**FlightConfig:** Configures a RestTemplate for making API requests.

**WebConfig:** Sets up CORS to allow cross-origin requests.



## Running the application locally
1. CLone or download the repository.
2. Open the project in your favorite IDE.
3. Set the environment variables. (Database credentials, apikey.)
4. Modify the application.properties file in the resources folder to match your database configuration. (Update the database URL, username, and password as needed.)
5. Run the application.
6. Run the frontend.
````
npm install
npm run dev
````

## Data:
The data for the application is fetched from the AviationStack API (https://aviationstack.com/). 

The DataLoader class populates the database with initial data on flights and seats.

Flight Data: A set of predefined flights with details such as departure and arrival cities, seat capacity, flight duration, airline, and departure times. 
Seat Data: Randomized seat information is created for each flight. The seat information includes:

Seat number (e.g., A1, B1, etc.)
Seat status, which is randomly assigned as either "Available" or "Booked" based on a 20% chance of being booked.
Extra legroom, randomly assigned with a 10% chance.
Seat type (e.g., "Window", "Middle", "Aisle"), which depends on the seat column (A to F).
## Notes:
The project was of a manageable difficulty level for me. However, I wish I had started earlier, which would have allowed me to add more features and refine the logic further (e.g., using variables for API keys, adding first-class logic, retrieving all data from APIs, implementing user logic, etc.).

The entire project took me about around 30 hours to complete.

The most difficult part of the project for me was at the beginning, when I couldn't see how everything should fit together. Because of this, I often came up with new ideas, but then things wouldn’t work as expected. I also had trouble getting data from the API at times, which led to many errors. This was frustrating and slowed me down. Although I understood the main goal, some tasks, like working with the API and fixing errors, turned out to be harder than I thought.

ChatGPT helped generate sample data (such as for the database and some website text). For example, in the EmailSubscriptipton file, I used AI-generated code for the subscribeNewsletter() method, but I modified and extended it.

Additionally, the entire README was reworded by ChatGPT. While the original content was written by me, the AI helped to enhance it.
