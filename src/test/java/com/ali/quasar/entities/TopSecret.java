package com.ali.quasar.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@NoArgsConstructor
@SuperBuilder
public class TopSecret {
    private List<SatelliteMessage> satellites;
}
