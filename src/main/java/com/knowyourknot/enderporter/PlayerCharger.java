package com.knowyourknot.enderporter;

import java.util.ArrayList;

import net.minecraft.entity.player.PlayerEntity;

public class PlayerCharger {
    private ArrayList<PlayerCharge> playerCharges;

    public PlayerCharger() {
        this.playerCharges = new ArrayList<>();
    }

    public void addPlayer(PlayerEntity player) {
        PlayerCharge charge = new PlayerCharge(player);
        this.playerCharges.add(charge);
    }

    public int getPlayerNo() {
        return this.playerCharges.size();
    }

    public boolean isEmpty() {
        return (this.getPlayerNo() == 0);
    }

    // returns true if the player was found
    public boolean removePlayer(PlayerEntity player) {
        int index = getPlayerIndex(player);
        if (index != -1) {
            this.playerCharges.remove(index);
            return true;
        }
        return false;
    }

    // returns -1 if player not found
    private int getPlayerIndex(PlayerEntity player) {
        for (int i = 0; i < this.playerCharges.size(); i++) {
            if (this.playerCharges.get(i).isPlayer(player)) {
                return i;
            }
        }
        return -1;
    }

    public boolean isPlayerCharging(PlayerEntity player) {
        return (this.getPlayerIndex(player) != -1);
    }

    private PlayerCharge getPlayerChargeFromIndex(int i) {
        return this.playerCharges.get(i);
    }
    
    private PlayerCharge getPlayerCharge(PlayerEntity player) {
        int index = this.getPlayerIndex(player);
        if (index != -1) {
            return getPlayerChargeFromIndex(index);
        }
        return null;
    }

    // returns -1 if player not found
    public int getCharge(PlayerEntity player) {
        PlayerCharge playerCharge = this.getPlayerCharge(player);
        if (playerCharge != null) {
            return playerCharge.getCharge();
        }
        return -1;
    }

    public void setCharge(PlayerEntity player, int value) {
        PlayerCharge charge = this.getPlayerCharge(player);
        if (charge != null) {
            charge.setCharge(value);
        }
    }

    public void incrementAll(int increment) {
        for (int i = 0; i < this.playerCharges.size(); i++) {
            this.playerCharges.get(i).incrementCharge(increment);
        }
    }
}