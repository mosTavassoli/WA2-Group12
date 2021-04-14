package com.wa2.demo.services.imp

import com.wa2.demo.dto.CustomerDTO
import com.wa2.demo.dto.WalletDTO
import com.wa2.demo.dto.toWalletDTO
import com.wa2.demo.entities.Customer
import com.wa2.demo.entities.Transaction
import com.wa2.demo.entities.Wallet
import com.wa2.demo.repositories.CustomerRepository
import com.wa2.demo.repositories.WalletRepository
import com.wa2.demo.services.WalletService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service @Transactional
class WalletServiceImpl (val walletRepository: WalletRepository, val customerRepository: CustomerRepository): WalletService {




    override fun addNewWallet(customerDTO: CustomerDTO) : WalletDTO{
//       val wallet : Wallet =  walletRepository.findByWalletId(walletDTO.walletId!!)
        //TODO
//        return walletDTO

//        Check if Customer exists

        try {
            val customer: Customer? = customerDTO.customerId?.let { customerRepository.findByCustomerId(it) }

            println("From DB")
            println(customer)
            println(customer?.email)


//        Add new wallet to customer

            val wallet = Wallet(null, customer, BigDecimal(0) , mutableSetOf<Transaction>() , mutableSetOf<Transaction>())

            val returnWallet = walletRepository.save(wallet)

            println(returnWallet)
            println(returnWallet.walletId)


            return returnWallet.toWalletDTO()




//        Return new wallet

//        println("From service")
//        println(customerDTO)





        }
        catch(e: Exception){

            println(e.message.toString())

        }

        return WalletDTO()

    }

    override fun getWalletById(walletId: Long): WalletDTO? {
        TODO("Not yet implemented")
    }
}