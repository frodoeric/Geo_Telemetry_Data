package com.eric.data.repository;

import com.eric.data.model.TGData;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DataRespository extends IBaseRepository<TGData, UUID> {
}
