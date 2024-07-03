package com.bogdanmierloiu.SpringAI.config;

import com.google.common.util.concurrent.ListenableFuture;
import io.milvus.client.MilvusClient;
import io.milvus.grpc.*;
import io.milvus.param.LogLevel;
import io.milvus.param.R;
import io.milvus.param.RetryParam;
import io.milvus.param.RpcStatus;
import io.milvus.param.alias.AlterAliasParam;
import io.milvus.param.alias.CreateAliasParam;
import io.milvus.param.alias.DropAliasParam;
import io.milvus.param.bulkinsert.BulkInsertParam;
import io.milvus.param.bulkinsert.GetBulkInsertStateParam;
import io.milvus.param.bulkinsert.ListBulkInsertTasksParam;
import io.milvus.param.collection.*;
import io.milvus.param.control.*;
import io.milvus.param.credential.CreateCredentialParam;
import io.milvus.param.credential.DeleteCredentialParam;
import io.milvus.param.credential.ListCredUsersParam;
import io.milvus.param.credential.UpdateCredentialParam;
import io.milvus.param.dml.*;
import io.milvus.param.highlevel.collection.CreateSimpleCollectionParam;
import io.milvus.param.highlevel.collection.ListCollectionsParam;
import io.milvus.param.highlevel.collection.response.ListCollectionsResponse;
import io.milvus.param.highlevel.dml.*;
import io.milvus.param.highlevel.dml.response.*;
import io.milvus.param.index.*;
import io.milvus.param.partition.*;
import io.milvus.param.resourcegroup.*;
import io.milvus.param.role.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class CustomMilvusClient implements MilvusClient{


    @Override
    public MilvusClient withTimeout(long timeout, TimeUnit timeoutUnit) {
        return null;
    }

    @Override
    public MilvusClient withRetry(RetryParam retryParam) {
        return null;
    }

    @Override
    public MilvusClient withRetry(int retryTimes) {
        return null;
    }

    @Override
    public MilvusClient withRetryInterval(long interval, TimeUnit timeUnit) {
        return null;
    }

    @Override
    public void setLogLevel(LogLevel level) {

    }

    @Override
    public void close(long maxWaitSeconds) throws InterruptedException {

    }

    @Override
    public R<Boolean> hasCollection(HasCollectionParam requestParam) {
        return null;
    }

    @Override
    public R<RpcStatus> createDatabase(CreateDatabaseParam requestParam) {
        return null;
    }

    @Override
    public R<RpcStatus> dropDatabase(DropDatabaseParam requestParam) {
        return null;
    }

    @Override
    public R<ListDatabasesResponse> listDatabases() {
        return null;
    }

    @Override
    public R<RpcStatus> createCollection(CreateCollectionParam requestParam) {
        return null;
    }

    @Override
    public R<RpcStatus> dropCollection(DropCollectionParam requestParam) {
        return null;
    }

    @Override
    public R<RpcStatus> loadCollection(LoadCollectionParam requestParam) {
        return null;
    }

    @Override
    public R<RpcStatus> releaseCollection(ReleaseCollectionParam requestParam) {
        return null;
    }

    @Override
    public R<DescribeCollectionResponse> describeCollection(DescribeCollectionParam requestParam) {
        return null;
    }

    @Override
    public R<GetCollectionStatisticsResponse> getCollectionStatistics(GetCollectionStatisticsParam requestParam) {
        return null;
    }

    @Override
    public R<RpcStatus> renameCollection(RenameCollectionParam requestParam) {
        return null;
    }

    @Override
    public R<ShowCollectionsResponse> showCollections(ShowCollectionsParam requestParam) {
        return null;
    }

    @Override
    public R<RpcStatus> alterCollection(AlterCollectionParam requestParam) {
        return null;
    }

    @Override
    public R<FlushResponse> flush(FlushParam requestParam) {
        return null;
    }

    @Override
    public R<FlushAllResponse> flushAll(boolean syncFlushAll, long syncFlushAllWaitingInterval, long syncFlushAllTimeout) {
        return null;
    }

    @Override
    public R<RpcStatus> createPartition(CreatePartitionParam requestParam) {
        return null;
    }

    @Override
    public R<RpcStatus> dropPartition(DropPartitionParam requestParam) {
        return null;
    }

    @Override
    public R<Boolean> hasPartition(HasPartitionParam requestParam) {
        return null;
    }

    @Override
    public R<RpcStatus> loadPartitions(LoadPartitionsParam requestParam) {
        return null;
    }

    @Override
    public R<RpcStatus> releasePartitions(ReleasePartitionsParam requestParam) {
        return null;
    }

    @Override
    public R<GetPartitionStatisticsResponse> getPartitionStatistics(GetPartitionStatisticsParam requestParam) {
        return null;
    }

    @Override
    public R<ShowPartitionsResponse> showPartitions(ShowPartitionsParam requestParam) {
        return null;
    }

    @Override
    public R<RpcStatus> createAlias(CreateAliasParam requestParam) {
        return null;
    }

    @Override
    public R<RpcStatus> dropAlias(DropAliasParam requestParam) {
        return null;
    }

    @Override
    public R<RpcStatus> alterAlias(AlterAliasParam requestParam) {
        return null;
    }

    @Override
    public R<RpcStatus> createIndex(CreateIndexParam requestParam) {
        return null;
    }

    @Override
    public R<RpcStatus> dropIndex(DropIndexParam requestParam) {
        return null;
    }

    @Override
    public R<DescribeIndexResponse> describeIndex(DescribeIndexParam requestParam) {
        return null;
    }

    @Override
    public R<GetIndexStateResponse> getIndexState(GetIndexStateParam requestParam) {
        return null;
    }

    @Override
    public R<GetIndexBuildProgressResponse> getIndexBuildProgress(GetIndexBuildProgressParam requestParam) {
        return null;
    }

    @Override
    public R<MutationResult> insert(InsertParam requestParam) {
        return null;
    }

    @Override
    public ListenableFuture<R<MutationResult>> insertAsync(InsertParam requestParam) {
        return null;
    }

    @Override
    public R<MutationResult> upsert(UpsertParam requestParam) {
        return null;
    }

    @Override
    public ListenableFuture<R<MutationResult>> upsertAsync(UpsertParam requestParam) {
        return null;
    }

    @Override
    public R<MutationResult> delete(DeleteParam requestParam) {
        return null;
    }

    @Override
    public R<SearchResults> search(SearchParam requestParam) {
        return null;
    }

    @Override
    public ListenableFuture<R<SearchResults>> searchAsync(SearchParam requestParam) {
        return null;
    }

    @Override
    public R<QueryResults> query(QueryParam requestParam) {
        return null;
    }

    @Override
    public ListenableFuture<R<QueryResults>> queryAsync(QueryParam requestParam) {
        return null;
    }

    @Override
    public R<GetMetricsResponse> getMetrics(GetMetricsParam requestParam) {
        return null;
    }

    @Override
    public R<GetFlushStateResponse> getFlushState(GetFlushStateParam requestParam) {
        return null;
    }

    @Override
    public R<GetFlushAllStateResponse> getFlushAllState(GetFlushAllStateParam requestParam) {
        return null;
    }

    @Override
    public R<GetPersistentSegmentInfoResponse> getPersistentSegmentInfo(GetPersistentSegmentInfoParam requestParam) {
        return null;
    }

    @Override
    public R<GetQuerySegmentInfoResponse> getQuerySegmentInfo(GetQuerySegmentInfoParam requestParam) {
        return null;
    }

    @Override
    public R<GetReplicasResponse> getReplicas(GetReplicasParam requestParam) {
        return null;
    }

    @Override
    public R<RpcStatus> loadBalance(LoadBalanceParam requestParam) {
        return null;
    }

    @Override
    public R<GetCompactionStateResponse> getCompactionState(GetCompactionStateParam requestParam) {
        return null;
    }

    @Override
    public R<ManualCompactionResponse> manualCompact(ManualCompactParam requestParam) {
        return null;
    }

    @Override
    public R<GetCompactionPlansResponse> getCompactionStateWithPlans(GetCompactionPlansParam requestParam) {
        return null;
    }

    @Override
    public R<RpcStatus> createCredential(CreateCredentialParam requestParam) {
        return null;
    }

    @Override
    public R<RpcStatus> updateCredential(UpdateCredentialParam requestParam) {
        return null;
    }

    @Override
    public R<RpcStatus> deleteCredential(DeleteCredentialParam requestParam) {
        return null;
    }

    @Override
    public R<ListCredUsersResponse> listCredUsers(ListCredUsersParam requestParam) {
        return null;
    }

    @Override
    public R<RpcStatus> createRole(CreateRoleParam requestParam) {
        return null;
    }

    @Override
    public R<RpcStatus> dropRole(DropRoleParam requestParam) {
        return null;
    }

    @Override
    public R<RpcStatus> addUserToRole(AddUserToRoleParam requestParam) {
        return null;
    }

    @Override
    public R<RpcStatus> removeUserFromRole(RemoveUserFromRoleParam requestParam) {
        return null;
    }

    @Override
    public R<SelectRoleResponse> selectRole(SelectRoleParam requestParam) {
        return null;
    }

    @Override
    public R<SelectUserResponse> selectUser(SelectUserParam requestParam) {
        return null;
    }

    @Override
    public R<RpcStatus> grantRolePrivilege(GrantRolePrivilegeParam requestParam) {
        return null;
    }

    @Override
    public R<RpcStatus> revokeRolePrivilege(RevokeRolePrivilegeParam requestParam) {
        return null;
    }

    @Override
    public R<SelectGrantResponse> selectGrantForRole(SelectGrantForRoleParam requestParam) {
        return null;
    }

    @Override
    public R<SelectGrantResponse> selectGrantForRoleAndObject(SelectGrantForRoleAndObjectParam requestParam) {
        return null;
    }

    @Override
    public R<ImportResponse> bulkInsert(BulkInsertParam requestParam) {
        return null;
    }

    @Override
    public R<GetImportStateResponse> getBulkInsertState(GetBulkInsertStateParam requestParam) {
        return null;
    }

    @Override
    public R<ListImportTasksResponse> listBulkInsertTasks(ListBulkInsertTasksParam requestParam) {
        return null;
    }

    @Override
    public R<CheckHealthResponse> checkHealth() {
        return null;
    }

    @Override
    public R<GetVersionResponse> getVersion() {
        return null;
    }

    @Override
    public R<GetLoadingProgressResponse> getLoadingProgress(GetLoadingProgressParam requestParam) {
        return null;
    }

    @Override
    public R<GetLoadStateResponse> getLoadState(GetLoadStateParam requestParam) {
        return null;
    }

    @Override
    public R<RpcStatus> createResourceGroup(CreateResourceGroupParam requestParam) {
        return null;
    }

    @Override
    public R<RpcStatus> dropResourceGroup(DropResourceGroupParam requestParam) {
        return null;
    }

    @Override
    public R<ListResourceGroupsResponse> listResourceGroups(ListResourceGroupsParam requestParam) {
        return null;
    }

    @Override
    public R<DescribeResourceGroupResponse> describeResourceGroup(DescribeResourceGroupParam requestParam) {
        return null;
    }

    @Override
    public R<RpcStatus> transferNode(TransferNodeParam requestParam) {
        return null;
    }

    @Override
    public R<RpcStatus> transferReplica(TransferReplicaParam requestParam) {
        return null;
    }

    @Override
    public R<RpcStatus> createCollection(CreateSimpleCollectionParam requestParam) {
        return null;
    }

    @Override
    public R<ListCollectionsResponse> listCollections(ListCollectionsParam requestParam) {
        return null;
    }

    @Override
    public R<InsertResponse> insert(InsertRowsParam requestParam) {
        return null;
    }

    @Override
    public R<DeleteResponse> delete(DeleteIdsParam requestParam) {
        return null;
    }

    @Override
    public R<GetResponse> get(GetIdsParam requestParam) {
        return null;
    }

    @Override
    public R<QueryResponse> query(QuerySimpleParam requestParam) {
        return null;
    }

    @Override
    public R<SearchResponse> search(SearchSimpleParam requestParam) {
        return null;
    }
}
