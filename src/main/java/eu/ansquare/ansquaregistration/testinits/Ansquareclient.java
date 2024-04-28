package eu.ansquare.ansquaregistration.testinits;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

import static eu.ansquare.ansquaregistration.testinits.Ansquaregistration.RG;

public class Ansquareclient implements ClientModInitializer {
    @Override
    public void onInitializeClient(ModContainer mod) {
        System.out.println("Sout");
        RG.clientRegister();
    }
}
