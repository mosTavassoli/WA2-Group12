package com.wa2.demo.services.imp

import com.wa2.demo.dto.CustomerDTO
import com.wa2.demo.dto.WalletDTO
import com.wa2.demo.dto.toWalletDTO
import com.wa2.demo.domain.Customer
import com.wa2.demo.domain.Transaction
import com.wa2.demo.domain.Wallet
import com.wa2.demo.repositories.CustomerRepository
import com.wa2.demo.repositories.WalletRepository
import com.wa2.demo.services.WalletService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service @Transactional
class WalletServiceImpl (val walletRepository: WalletRepository, val customerRepository: CustomerRepository): WalletService {
    override fun addNewWallet(customerDTO: CustomerDTO) : WalletDTO{
        try {
            val customer: Customer? = customerDTO.customerId?.let { customerRepository.findByCustomerId(it) }
            val wallet = Wallet(null, customer, BigDecimal(0) , mutableSetOf<Transaction>() , mutableSetOf<Transaction>())
            val returnWallet = walletRepository.save(wallet)
            return returnWallet.toWalletDTO()
        }
        catch(e: Exception){
            println(e.message.toString())
        }
        return WalletDTO()
    }

    override fun getWalletById(walletId: Long): WalletDTO? {
        val wallet : Wallet =  walletRepository.findByWalletId(walletId)
        return WalletDTO.getByWallet(wallet)
    }
}