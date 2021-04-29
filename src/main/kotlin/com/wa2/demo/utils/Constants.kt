package com.wa2.demo.utils

class Constants {
    companion object {
        const val CREATE_WALLET = ""
        const val GET_WALLET_DETAILS = "/{walletId}"
        const val CREATE_TRANSACTION = "/{walletId}/transactions"
        const val GET_TRANSACTION_BY_DATE_TIME = "/{walletId}/transactions"
        const val GET_TRANSACTION_DETAILS = "/{walletId}/transactions/{transactionId}"

        const val REGISTER = "/auth/register"
        const val SIGN_IN = "/auth/signin"
        const val REGISTRATION_CONFORMATION = "/auth/registrationConfirm?token={token}"
        const val REGISTRATION_CONFIRMATION_FOR_MAIL_LINK = "http://localhost:8080/auth/registrationConfirm?token="

        // Minutes after which the token is invalid
        const val ExpiryTimeInMinutes = 10
    }
}