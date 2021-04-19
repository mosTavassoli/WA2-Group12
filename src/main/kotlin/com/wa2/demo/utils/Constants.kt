package com.wa2.demo.utils

class Constants {
    companion object {
        const val CREATE_WALLET = ""
        const val GET_WALLET_DETAILS = "/{walletId}"
        const val CREATE_TRANSACTION = "/{walletId}/transactions"
        const val GET_TRANSACTION_BY_DATE_TIME = "/{walletId}/transactions"
        const val GET_TRANSACTION_DETAILS = "/{walletId}/transactions/{transactionId}"
    }
}