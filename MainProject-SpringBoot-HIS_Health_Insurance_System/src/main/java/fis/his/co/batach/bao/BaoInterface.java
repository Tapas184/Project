package fis.his.co.batach.bao;

import java.util.List;

import fis.his.co.ed.model.TriggerModel;

public interface BaoInterface {

	public List<TriggerModel> getTriggerByBucket(String status, int bucketSize, int bucketNumber) throws ClassNotFoundException;
}
