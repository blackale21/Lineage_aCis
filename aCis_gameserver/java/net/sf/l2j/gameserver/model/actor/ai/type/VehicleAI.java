package net.sf.l2j.gameserver.model.actor.ai.type;

import net.sf.l2j.gameserver.model.L2Skill;
import net.sf.l2j.gameserver.model.WorldObject;
import net.sf.l2j.gameserver.model.actor.Creature;
import net.sf.l2j.gameserver.model.actor.Vehicle;
import net.sf.l2j.gameserver.model.actor.instance.Player;
import net.sf.l2j.gameserver.model.location.SpawnLocation;
import net.sf.l2j.gameserver.network.serverpackets.VehicleDeparture;
import net.sf.l2j.gameserver.network.serverpackets.VehicleInfo;
import net.sf.l2j.gameserver.network.serverpackets.VehicleStarted;

public class VehicleAI extends CreatureAI
{
	public VehicleAI(Vehicle boat)
	{
		super(boat);
	}
	
	@Override
	protected void moveTo(int x, int y, int z)
	{
		if (!_actor.isMovementDisabled())
		{
			if (!_clientMoving)
				_actor.broadcastPacket(new VehicleStarted(getActor(), 1));
			
			_clientMoving = true;
			_actor.moveToLocation(x, y, z, 0);
			_actor.broadcastPacket(new VehicleDeparture(getActor()));
		}
	}
	
	@Override
	protected void clientStopMoving(SpawnLocation loc)
	{
		if (_actor.isMoving())
			_actor.stopMove(loc);
		
		if (_clientMoving || loc != null)
		{
			_clientMoving = false;
			_actor.broadcastPacket(new VehicleStarted(getActor(), 0));
			_actor.broadcastPacket(new VehicleInfo(getActor()));
		}
	}
	
	@Override
	public void describeStateToPlayer(Player player)
	{
		if (_clientMoving)
			player.sendPacket(new VehicleDeparture(getActor()));
	}
	
	@Override
	public Vehicle getActor()
	{
		return (Vehicle) _actor;
	}
	
	@Override
	protected void onIntentionAttack(Creature target)
	{
	}
	
	@Override
	protected void onIntentionCast(L2Skill skill, WorldObject target)
	{
	}
	
	@Override
	protected void onIntentionFollow(Creature target)
	{
	}
	
	@Override
	protected void onIntentionPickUp(WorldObject item)
	{
	}
	
	@Override
	protected void onIntentionInteract(WorldObject object)
	{
	}
	
	@Override
	protected void onEvtAttacked(Creature attacker)
	{
	}
	
	@Override
	protected void onEvtAggression(Creature target, int aggro)
	{
	}
	
	@Override
	protected void onEvtStunned(Creature attacker)
	{
	}
	
	@Override
	protected void onEvtSleeping(Creature attacker)
	{
	}
	
	@Override
	protected void onEvtRooted(Creature attacker)
	{
	}
	
	@Override
	protected void onEvtCancel()
	{
	}
	
	@Override
	protected void onEvtDead()
	{
	}
	
	@Override
	protected void onEvtFakeDeath()
	{
	}
	
	@Override
	protected void onEvtFinishCasting()
	{
	}
	
	@Override
	protected void clientActionFailed()
	{
	}
	
	@Override
	protected void moveToPawn(WorldObject pawn, int offset)
	{
	}
	
	@Override
	protected void clientStoppedMoving()
	{
	}
}