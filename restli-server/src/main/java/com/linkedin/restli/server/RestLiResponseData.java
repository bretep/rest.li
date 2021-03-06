/*
   Copyright (c) 2014 LinkedIn Corp.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package com.linkedin.restli.server;


import com.linkedin.data.template.RecordTemplate;
import com.linkedin.restli.common.CollectionMetadata;
import com.linkedin.restli.common.ErrorResponse;

import java.util.List;
import java.util.Map;


/**
 * An abstraction that encapsulates outgoing response data.
 *
 * @author nshankar
 *
 */
public interface RestLiResponseData
{
  /**
   * Determine if the response is an entity response or not.
   *
   * @return true if the response is a RecordTemplate; else false.
   */
  boolean isEntityResponse();

  /**
   * Determine if the response is collection response or not.
   *
   * @return true if the response corresponds to a collection request; else false.
   */
  boolean isCollectionResponse();

  /**
   * Determine if the data corresponds to a batch response.
   *
   * @return true if the response is a batch response; else false.
   */
  boolean isBatchResponse();

  /**
   * Determine if the data corresponds to an error response.
   *
   * @return true if the response is an error response; else false.
   */
  boolean isErrorResponse();

  /**
   * Obtain the entity response.
   *
   * @return the entity if one exists; else null.
   */
  RecordTemplate getEntityResponse();

  /**
   * Set the response entity. This is permitted only for GET, ACTION, and CREATE method types.
   *
   * @param entity
   *          New value of the entity.
   * @throws RestLiResponseDataException
   *           is thrown if this method is invoked for any method types other than the
   *           aforementioned ones.
   */
  void setEntityResponse(RecordTemplate entity) throws RestLiResponseDataException;

  /**
   * Obtain the error response.
   *
   * @return the error response if one exists; else null.
   */
  ErrorResponse getErrorResponse();

  /**
   * Set the error response. As a side effect of this operation, other responses (collection, batch,
   * and simple) will be cleared.
   *
   * @param errorResponse
   *          New value of the error response.
   */
  void setErrorResponse(ErrorResponse errorResponse);

  /**
   * Obtain a mutable {@link List} of response entities.
   *
   * @return List of response entities if they exists; else null.
   */
  List<? extends RecordTemplate> getCollectionResponse();

  /**
   * Set collection response entities. This is permitted only for FINDER, GET_ALL, and BATCH_CREATE
   * method types.
   *
   * @param entity
   *          New value of collection response entity.
   * @throws RestLiResponseDataException
   *           is thrown if this method is invoked for any method types other than the
   *           aforementioned ones.
   */
  void setCollectionResponse(List<? extends RecordTemplate> responseEntities) throws RestLiResponseDataException;

  /**
   * Get paganation info associated with collection response.
   *
   * @return {@link CollectionMetadata}
   */
  CollectionMetadata getCollectionResponsePaging();

  /**
   * Set paganation info associated with collection response. This is permitted only for FINDER and
   * GET_ALL method types.
   *
   * @param paging
   *          {@link CollectionMetadata}
   * @throws RestLiResponseDataException
   *           is thrown if this method is invoked for any method types other than the
   *           aforementioned ones.
   */
  void setCollectionResponsePaging(CollectionMetadata paging) throws RestLiResponseDataException;

  /**
   * Get custom metadata associated with collection response.
   *
   * @return Custom metadata associated with the collection response.
   */
  RecordTemplate getCollectionResponseCustomMetadata();

  /**
   * Set custom metadata to be associated with the collection response. This is permitted only for
   * FINDER and GET_ALL method types.
   *
   * @param metadata
   *          Custom metadata associated with the collection response.
   * @throws RestLiResponseDataException
   *           is thrown if this method is invoked for any method types other than the
   *           aforementioned ones.
   */
  void setCollectionResponseCustomMetadata(RecordTemplate metadata) throws RestLiResponseDataException;

  /**
   * Obtain a mutable key response entity map. This map is populated primarily for batch responses.
   *
   * @return mutable key entity map.
   */
  Map<?, ? extends RecordTemplate> getBatchResponseMap();

  /**
   * Set batch response entities. This is permitted only for BATCH_GET, BATCH_UPDATE,
   * BATCH_PARTIAL_UPDATE, and BATCH_DELETE method types.
   *
   * @param entity
   *          New value of collection response entity.
   * @throws RestLiResponseDataException
   *           is thrown if this method is invoked for any method types other than the
   *           aforementioned ones.
   */
  void setBatchKeyResponseMap(Map<?, ? extends RecordTemplate> batchEntityMap) throws RestLiResponseDataException;
}
