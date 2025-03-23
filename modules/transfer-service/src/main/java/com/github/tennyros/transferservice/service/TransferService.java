package com.github.tennyros.transferservice.service;

import com.github.tennyros.transferservice.model.TransferRestModel;

public interface TransferService {

    boolean transfer(TransferRestModel transferRestModel);

}
