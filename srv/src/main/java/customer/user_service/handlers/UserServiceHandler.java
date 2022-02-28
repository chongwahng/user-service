package customer.user_service.handlers;

import org.springframework.stereotype.Component;

import com.sap.cds.services.cds.CdsReadEventContext;
import com.sap.cds.services.cds.CdsService;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;

import com.sap.cloud.sdk.cloudplatform.connectivity.Destination;
import com.sap.cloud.sdk.cloudplatform.connectivity.DestinationAccessor;

import com.sap.cloud.sdk.s4hana.connectivity.rfc.BapiRequest;
import com.sap.cloud.sdk.s4hana.connectivity.rfc.BapiRequestResult;
import com.sap.cloud.sdk.s4hana.connectivity.exception.RequestExecutionException;

@Component
@ServiceName("UserService")
public class UserServiceHandler implements EventHandler {

    @On(event = CdsService.EVENT_READ, entity = "UserService.Users")
    public void onRead(CdsReadEventContext context) {
        final Destination dest = DestinationAccessor.getDestination("S4HANA_PSD_RFC_500");

        try {
            final BapiRequestResult result = new BapiRequest("BAPI_USER_GETLIST")
                    .withExporting("WITH_USERNAME", "BAPIUSMISC-WITH_NAME", "X")
                    .withTable("USERLIST", "BAPIUSNAME").end()
                    .withTableAsReturn("BAPIRET2")
                    .execute(dest);                      
            
        } catch (final RequestExecutionException e) {
            e.printStackTrace();
        }
    }
}

