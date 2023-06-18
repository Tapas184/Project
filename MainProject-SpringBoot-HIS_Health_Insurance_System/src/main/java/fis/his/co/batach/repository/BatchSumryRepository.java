package fis.his.co.batach.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import fis.his.co.batach.entity.BatchSummary;

public interface BatchSumryRepository extends JpaRepository<BatchSummary, Serializable> {

}
