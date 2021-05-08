package com.wa2.demo.services

import com.wa2.demo.dto.WalletDTO


interface WalletService {
    fun addNewWallet (customerId: Long) : WalletDTO
    fun getWalletById (walletId: Long) : WalletDTO?
}