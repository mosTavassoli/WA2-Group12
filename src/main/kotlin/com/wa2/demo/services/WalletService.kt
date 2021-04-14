package com.wa2.demo.services

import com.wa2.demo.dto.CustomerDTO
import com.wa2.demo.dto.WalletDTO


interface WalletService {
    fun addNewWallet (customerDTO: CustomerDTO) : WalletDTO
    fun getWalletById (walletId: Long) : WalletDTO?
}