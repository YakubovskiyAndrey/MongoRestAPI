package ua.yakubovskiy.MongoRestAPI.dto;

import lombok.extern.jackson.Jacksonized;

@Jacksonized
public record RestResponse(String result) {
}