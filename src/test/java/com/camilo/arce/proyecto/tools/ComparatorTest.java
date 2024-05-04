package com.camilo.arce.proyecto.tools;


import com.camilo.arce.proyecto.dto.DiscoveryDto;
import com.camilo.arce.proyecto.dto.ProviderDetailsDto;
import com.camilo.arce.proyecto.dto.ProvidersDto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ComparatorTest {

    public ProvidersDto createTestProvider(Long id){
        ProvidersDto providerDto = new ProvidersDto();
        providerDto.setProviderId(id);
        providerDto.setName("testProvider");
        providerDto.setDiscoveryUrl("testDiscoveryUrl");
        providerDto.setClientId("testClient");
        return providerDto;
    }

    @Test
    public void discoveryComparatorTest() {
        final DiscoveryDto discovery1 = new DiscoveryDto();
        final DiscoveryDto discovery2 = new DiscoveryDto();
        final DiscoveryDto discovery3 = new DiscoveryDto();
        final DiscoveryDto discovery4 = new DiscoveryDto();
        final List<DiscoveryDto> discoveryList = new ArrayList<DiscoveryDto>();

        discovery1.setDiscoveryId(1L);
        discovery1.setIssuer("testIssuer");
        discovery1.setAuthEndpoint("testAuthEndpoint");
        discovery1.setTokenEndpoint("testTokenEndpoint");
        discovery1.setJwksUri("testJwksUri");
        discovery1.setProviders(createTestProvider(4L));
        discoveryList.add(discovery1);

        discovery2.setDiscoveryId(2L);
        discovery2.setIssuer("testIssuer");
        discovery2.setAuthEndpoint("testAuthEndpoint");
        discovery2.setTokenEndpoint("testTokenEndpoint");
        discovery2.setJwksUri("testJwksUri");
        discovery2.setProviders(createTestProvider(3L));
        discoveryList.add(discovery2);

        discovery3.setDiscoveryId(3L);
        discovery3.setIssuer("testIssuer");
        discovery3.setAuthEndpoint("testAuthEndpoint");
        discovery3.setTokenEndpoint("testTokenEndpoint");
        discovery3.setJwksUri("testJwksUri");
        discovery3.setProviders(createTestProvider(2L));
        discoveryList.add(discovery3);

        discovery4.setDiscoveryId(4L);
        discovery4.setIssuer("testIssuer");
        discovery4.setAuthEndpoint("testAuthEndpoint");
        discovery4.setTokenEndpoint("testTokenEndpoint");
        discovery4.setJwksUri("testJwksUri");
        discovery4.setProviders(createTestProvider(1L));
        discoveryList.add(discovery4);

        assertEquals(1L, discoveryList.get(0).getDiscoveryId());
        assertEquals(2L, discoveryList.get(1).getDiscoveryId());
        assertEquals(3L, discoveryList.get(2).getDiscoveryId());
        assertEquals(4L, discoveryList.get(3).getDiscoveryId());

        discoveryList.sort(new DiscoveryComparator());

        assertEquals(4L, discoveryList.get(0).getDiscoveryId());
        assertEquals(3L, discoveryList.get(1).getDiscoveryId());
        assertEquals(2L, discoveryList.get(2).getDiscoveryId());
        assertEquals(1L, discoveryList.get(3).getDiscoveryId());

    }

    @Test
    public void detailsComparatorTest() {
        final ProviderDetailsDto details1 = new ProviderDetailsDto();
        final ProviderDetailsDto details2 = new ProviderDetailsDto();
        final ProviderDetailsDto details3 = new ProviderDetailsDto();
        final ProviderDetailsDto details4 = new ProviderDetailsDto();
        final List<ProviderDetailsDto> detailsList = new ArrayList<ProviderDetailsDto>();

        details1.setProviderDetailsId(1L);
        details1.setProviders(createTestProvider(4L));
        detailsList.add(details1);

        details2.setProviderDetailsId(2L);
        details2.setProviders(createTestProvider(3L));
        detailsList.add(details2);

        details3.setProviderDetailsId(3L);
        details3.setProviders(createTestProvider(2L));
        detailsList.add(details3);

        details4.setProviderDetailsId(4L);
        details4.setProviders(createTestProvider(1L));
        detailsList.add(details4);

        assertEquals(1L, detailsList.get(0).getProviderDetailsId());
        assertEquals(2L, detailsList.get(1).getProviderDetailsId());
        assertEquals(3L, detailsList.get(2).getProviderDetailsId());
        assertEquals(4L, detailsList.get(3).getProviderDetailsId());

        detailsList.sort(new ProviderDetailsComparator());

        assertEquals(4L, detailsList.get(0).getProviderDetailsId());
        assertEquals(3L, detailsList.get(1).getProviderDetailsId());
        assertEquals(2L, detailsList.get(2).getProviderDetailsId());
        assertEquals(1L, detailsList.get(3).getProviderDetailsId());

    }


}
