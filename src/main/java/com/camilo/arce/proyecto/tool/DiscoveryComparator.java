package com.camilo.arce.proyecto.tool;

import com.camilo.arce.proyecto.dto.DiscoveryDto;

import java.util.Comparator;

public class DiscoveryComparator implements Comparator<DiscoveryDto> {
    @Override
    public int compare(DiscoveryDto o1, DiscoveryDto o2) {
        return Long.compare(o1.getProviders().getProviderId(), o2.getProviders().getProviderId());

    }
}
