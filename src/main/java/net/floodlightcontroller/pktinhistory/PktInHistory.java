package net.floodlightcontroller.pktinhistory;

import java.util.Collection;
import java.util.Map;

import org.openflow.protocol.OFMessage;
import org.openflow.protocol.OFType;

import net.floodlightcontroller.core.FloodlightContext;
import net.floodlightcontroller.core.IOFMessageListener;
import net.floodlightcontroller.core.IOFSwitch;
import net.floodlightcontroller.core.module.FloodlightModuleContext;
import net.floodlightcontroller.core.module.FloodlightModuleException;
import net.floodlightcontroller.core.module.IFloodlightModule;
import net.floodlightcontroller.core.module.IFloodlightService;

public class PktInHistory implements IFloodlightModule, IOFMessageListener
{
    @Override
    public String getName()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isCallbackOrderingPrereq(OFType type, String name)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isCallbackOrderingPostreq(OFType type, String name)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public net.floodlightcontroller.core.IListener.Command receive(IOFSwitch sw, OFMessage msg, FloodlightContext cntx)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<Class<? extends IFloodlightService>> getModuleServices()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<Class<? extends IFloodlightService>, IFloodlightService> getServiceImpls()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<Class<? extends IFloodlightService>> getModuleDependencies()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void init(FloodlightModuleContext context) throws FloodlightModuleException
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void startUp(FloodlightModuleContext context)
    {
        // TODO Auto-generated method stub
    }
}
