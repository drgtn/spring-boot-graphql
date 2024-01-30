package com.beecode.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import lombok.Value;

import java.util.UUID;
@Data
@Builder
public class Client {
    UUID id;
    String firstName;
    String lastName;
}
