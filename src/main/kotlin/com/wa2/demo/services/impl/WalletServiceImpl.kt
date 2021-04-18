package com.wa2.demo.services.impl

import com.wa2.demo.dto.WalletDTO
import com.wa2.demo.dto.toWalletDTO
import com.wa2.demo.domain.Customer
import com.wa2.demo.domain.Wallet
import com.wa2.demo.repositories.CustomerRepository
import com.wa2.demo.repositories.WalletRepository
import com.wa2.demo.services.WalletService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service
@Transactional
class WalletServiceImpl(
    @Autowired val walletRepository: WalletRepository,
    @Autowired val customerRepository: CustomerRepository
) : WalletService {
    override fun addNewWallet(customerId: Long): WalletDTO {
        try {
            val customerOp = customerRepository.findById(customerId)
            var customer = Customer()
            if (customerOp.isPresent)
                customer = customerOp.get()
            else throw Exception("Error: The Customer does not exist.")
            val wallet = Wallet()
            wallet.customer = customer
            wallet.currentAmount = BigDecimal(0)
            val returnWallet = walletRepository.save(wallet)
            return returnWallet.toWalletDTO()
        } catch (e: Exception) {
            throw e
        }
    }

    override fun getWalletById(walletId: Long): WalletDTO? {
        try {
            val wallet: Wallet = walletRepository.findByWalletId(walletId)
            return wallet.toWalletDTO()
        } catch (ex: Exception) {
            throw Exception("Error: The Wallet does not exist.")
        }
    }
}