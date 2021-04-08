package com.wa2.demo.repositories

import com.wa2.demo.entities.Wallet
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface WalletRepository : CrudRepository<Wallet, Long> {
    fun findByWalletId(walletId : Long) : Wallet
}