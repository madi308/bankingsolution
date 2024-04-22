# Banking Solution #

To run database and RabbitMQ, use `docker compose up` in the project root directory (append `-d` to run in detached mode).

To run the application, use `./gradlew bootRun`.

NB! The application is missing tests and also the balance after a transaction is not displayed.

Explanation of important choices in your solution

RabbitMQ: I decided to push messages directly to a queue because there is only one type of messages and one queue. In production the solution would be more complex. It would also not be the best idea to use admin account on clients.

Estimate on how many transactions can your account application can handle per second on your development machine

I ended up not doing load testing, but I can assume that the number is quite big, because there are no terribly complex computations and also database and RabbitMQ are running locally, so the exchange of data is fast.

Describe what do you have to consider to be able to scale applications horizontally

Database calls would be the most critical, because if there are multiple nodes, they might access the same accounts and balances simultaneously. This was also one of the reasons why I did not want to add balance after a transaction just by creating a new select query and decided to leave it as it is. I am not entirely sure but RabbitMQ would most likely get messed up using this setup as well. This could be easily fixed by setting up proper exchanges and additional accounts.
