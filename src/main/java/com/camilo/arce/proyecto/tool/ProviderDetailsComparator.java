package com.camilo.arce.proyecto.tool;

import com.camilo.arce.proyecto.dto.ProviderDetailsDto;

import java.util.Comparator;

public class ProviderDetailsComparator implements Comparator<ProviderDetailsDto> {

    @Override
    public int compare(ProviderDetailsDto o1, ProviderDetailsDto o2) {
        return Long.compare(o1.getProviders().getProviderId(), o2.getProviders().getProviderId());
    }
}
