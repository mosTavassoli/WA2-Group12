# Wallet Service

## REST API server

- POST `/wallet`
  - Create a new wallet for a given customer. In the RequestBody, there will be the Customer’s ID for which you want to create a wallet. The wallet created will initially have no money. Once the wallet is created, return a 201 (created) response status and the wallet itself as the response body.

- GET `/wallet/{walletId}`
  - Get the details of a wallet. The response body will be the requested wallet, and the response status 200 (ok).

- POST `/wallet/{walletId}/transaction`
  - Create a transaction taking the amount of money set in the body from the given wallet and transferring it to a second walletId, always defined in the body. Return the created transaction.

- GET `/wallet/{walletId}/transactions?from=<dateInMillis>&to=<dateInMillis>`
  - Get a list of transactions regarding a given wallet in a given time frame.

- POST `/wallet/{walletId}/transactions/{transactionId}`
  - Get the details of a single transaction.

- POST `/auth/register`
  - Register the user, the request should contain the username, valid email, name, surname, address, password, and confirm password.

- POST `/auth/signin`
  - After email confirmation, the User can log in to the system.

- GET `/auth/registrationConfirm?token=<token>`
  - when this is invoked, retrieve from the repository the EmailVerificationToken by the token String. Verify if it is not expired and enable the corresponding username. If the token is not found or it is expired, an exception is thrown.
