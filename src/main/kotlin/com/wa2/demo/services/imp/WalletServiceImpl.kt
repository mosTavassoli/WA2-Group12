package com.wa2.demo.services.imp

import com.wa2.demo.dto.WalletDTO
import com.wa2.demo.entities.Wallet
import com.wa2.demo.repositories.WalletRepository
import com.wa2.demo.services.WalletService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service @Transactional
class WalletServiceImpl (val walletRepository: WalletRepository): WalletService {

    override fun addNewWallet(walletDTO: WalletDTO) : WalletDTO{
       val wallet : Wallet =  walletRepository.findByWalletId(walletDTO.walletId!!)
        //TODO
        return walletDTO
    }

    override fun getWalletById(walletId: Long): WalletDTO? {
        TODO("Not yet implemented")
    }
}