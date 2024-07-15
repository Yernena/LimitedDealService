# Limited Time Deals Service

## Assumptions
- Deals have a start time and an end time.
- Users can only claim a deal if it's within the time period and there are remaining items.
- One user can only claim one item per deal. This is tracked using user IDs.

## Running the Application
- Compile the Java files using `javac Deal.java DealManager.java DealsApplication.java`.
- Run the application using `java DealsApplication`.

## Running the Tests
- Ensure JUnit 5 is set up in your project.
- Run the tests using `mvn test` if using Maven.

## Key Functionalities
- Create a new deal.
- End an existing deal.
- Update an existing deal (max items, end time).
- Claim an item from a deal. Requires a `userId`.

## Example Output
- The application will print the creation, claiming, and ending of deals along with any exceptions encountered.
