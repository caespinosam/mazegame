package com.mazegame.core.model.weapon;

/**
 * The expected behavior from a weapon whose ammo may be increased.
 * 
 * @author Cesar
 *
 */
public interface IWeaponRechargeable extends IWeapon {

  /**
   * Whether the weapon has no ammo.
   * 
   * @return true no ammo, false otherwise
   */
  boolean isEmpty();

  /**
   * Recharges the weapon .
   * 
   * @param units the unit of ammo to recharge
   */
  void rechargeAmmo(int units);

}
