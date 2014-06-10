package net.floodlightcontroller.mactracker;

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

import net.floodlightcontroller.core.IFloodlightProviderService;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.Set;
import net.floodlightcontroller.packet.Ethernet;
import org.openflow.util.HexString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MACTracker implements IOFMessageListener, IFloodlightModule
{
    protected IFloodlightProviderService floodlightProvider;
    protected Set macAddresses;
    protected static Logger logger;

    @Override
    public String getName()
    {
        logger.info("[DebugInfo][MACTracker] getName");
        return MACTracker.class.getSimpleName();
    }

    @Override
    public boolean isCallbackOrderingPrereq(OFType type, String name)
    {
        logger.info("[DebugInfo][MACTracker] isCallbackOrderingPrereq");
        return false;
    }

    @Override
    public boolean isCallbackOrderingPostreq(OFType type, String name)
    {
        logger.info("[DebugInfo][MACTracker] isCallbackOrderingPostreq");
        return false;
    }

    @Override
    public Collection<Class<? extends IFloodlightService>> getModuleServices()
    {
        logger.info("[DebugInfo][MACTracker] getModuleServices");
        return null;
    }

    @Override
    public Map<Class<? extends IFloodlightService>, IFloodlightService> getServiceImpls()
    {
        logger.info("[DebugInfo][MACTracker] getServiceImpls");
        return null;
    }

    @Override
    public Collection<Class<? extends IFloodlightService>> getModuleDependencies()
    {
        logger.info("[DebugInfo][MACTracker] getModuleDependencies");
        Collection<Class<? extends IFloodlightService>> l = new ArrayList<Class<? extends IFloodlightService>>();
        l.add(IFloodlightProviderService.class);
        return l;
    }

    @Override
    public void init(FloodlightModuleContext context) throws FloodlightModuleException
    {
        logger.info("[DebugInfo][MACTracker] init");
        floodlightProvider = context.getServiceImpl(IFloodlightProviderService.class);
        macAddresses = new ConcurrentSkipListSet<Long>();
        logger = LoggerFactory.getLogger(MACTracker.class);
    }

    @Override
    public void startUp(FloodlightModuleContext context)
    {
        logger.info("[DebugInfo][MACTracker]startUp");
        floodlightProvider.addOFMessageListener(OFType.PACKET_IN, this);
    }

    @Override
    public net.floodlightcontroller.core.IListener.Command receive(IOFSwitch sw, OFMessage msg, FloodlightContext cntx)
    {
        Ethernet eth = IFloodlightProviderService.bcStore.get(cntx, IFloodlightProviderService.CONTEXT_PI_PAYLOAD);

        Long sourceMACHash = Ethernet.toLong(eth.getSourceMACAddress());
        if (!macAddresses.contains(sourceMACHash))
        {
            macAddresses.add(sourceMACHash);
            logger.info("[DebugInfo][MACTracker]MAC Address: {} seen on switch: {}", HexString.toHexString(sourceMACHash), sw.getId());
        }
        return Command.CONTINUE;
    }
}
