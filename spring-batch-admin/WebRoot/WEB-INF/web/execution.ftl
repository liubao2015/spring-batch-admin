<#import "/spring.ftl" as spring />
<div id="step-execution">

    <#if stepExecutionInfo??>
        <h2>执行步骤的详细信息</h2>
        
        <#if stepExecutionInfo.stepExecution??>
            <#assign execution_url><@spring.url relativeUrl="${servletPath}/jobs/executions/${stepExecutionInfo.jobExecutionId?c}/steps/${stepExecutionInfo.id?c}/stop"/></#assign>
           <#if stepExecutionInfo.stepExecution.status=='STARTING'||stepExecutionInfo.stepExecution.status=='STARTED'>
            <form id="stopForm" action="${execution_url}" method="post">
                <#if stopRequest??>
                    <@spring.bind path="stopRequest" />
                    <@spring.showErrors separator="<br/>" classOrStyle="error" /><br/>
                </#if>
        
                <#assign stop_label="停止"/>
                <#assign stop_param="stop"/>
                
                <ol>
                    <li>
                          <input type="hidden" name="_method" value="DELETE"/>
                          <input id="stop" type="submit" value="${stop_label}" name="${stop_param}" />
                    </li>
                </ol>
           </form>
           </#if>
           
           <#if stepExecutionInfo.stepExecution.status=='FAILED'||stepExecutionInfo.stepExecution.status=='UNKNOWN'||stepExecutionInfo.stepExecution.status=='ABANDONED'>
           <#assign execution_url><@spring.url relativeUrl="${servletPath}/jobs/executions/${stepExecutionInfo.jobExecutionId?c}/steps/${stepExecutionInfo.name}/${stepExecutionInfo.id?c}/restart"/></#assign>
           <form id="stopForm" action="${execution_url}" method="post">
                <#if stopRequest??>
                    <@spring.bind path="stopRequest" />
                    <@spring.showErrors separator="<br/>" classOrStyle="error" /><br/>
                </#if>
        
                <#assign stop_label="重启"/>
                <#assign stop_param="restart"/>
                
                <ol>
                    <li>
                          <input id="stop" type="submit" value="${stop_label}" name="${stop_param}" />
                    </li>
                </ol>
           </form>
           </#if>
        </#if>
        
        <table title="Step Execution Details"
            class="bordered-table">
            <tr>
                <th>属性</th>
                <th>值</th>
            </tr>
            <tr class="name-sublevel1-odd">
                <td>ID</td>
                <td>${stepExecutionInfo.id}</td>
            </tr>
            <tr class="name-sublevel1-even">
                <#assign execution_url><@spring.url relativeUrl="${servletPath}/jobs/executions/${stepExecutionInfo.jobExecutionId?c}"/></#assign>
                <td>作业执行</td>
                <td><a href="${execution_url}">${stepExecutionInfo.jobExecutionId}</a></td>
            </tr>
            <tr class="name-sublevel1-odd">
                <td>作业名称</td>
                <td>${stepExecutionInfo.jobName}</td>
            </tr>
            <tr class="name-sublevel1-even">
                <td>步骤名称</td>
                <td>${stepExecutionInfo.name}</td>
            </tr>
            <tr class="name-sublevel1-odd">
                <td>开始日期</td>
                <td>${stepExecutionInfo.startDate}</td>
            </tr>
            <tr class="name-sublevel1-even">
                <td>开始时间</td>
                <td>${stepExecutionInfo.startTime}</td>
            </tr>
            <tr class="name-sublevel1-odd">
                <td>持续时间</td>
                <td>${stepExecutionInfo.duration}</td>
            </tr>
            <tr class="name-sublevel1-even">
                <td>状态</td>
                <td>${stepExecutionInfo.stepExecution.status}</td>
            </tr>
            <tr class="name-sublevel1-odd">
                <td>读次数</td>
                <td>${stepExecutionInfo.stepExecution.readCount}</td>
            </tr>
            <tr class="name-sublevel1-even">
                <td>写次数</td>
                <td>${stepExecutionInfo.stepExecution.writeCount}</td>
            </tr>
            <tr class="name-sublevel1-odd">
                <td>过滤器次数</td>
                <td>${stepExecutionInfo.stepExecution.filterCount}</td>
            </tr>
            <tr class="name-sublevel1-even">
                <td>跳过读次数</td>
                <td>${stepExecutionInfo.stepExecution.readSkipCount}</td>
            </tr>
            <tr class="name-sublevel1-odd">
                <td>跳过写次数</td>
                <td>${stepExecutionInfo.stepExecution.writeSkipCount}</td>
            </tr>
            <tr class="name-sublevel1-even">
                <td>跳过处理次数</td>
                <td>${stepExecutionInfo.stepExecution.processSkipCount}</td>
            </tr>
            <tr class="name-sublevel1-odd">
                <td>提交次数</td>
                <td>${stepExecutionInfo.stepExecution.commitCount}</td>
            </tr>
            <tr class="name-sublevel1-even">
                <td>回滚次数</td>
                <td>${stepExecutionInfo.stepExecution.rollbackCount}</td>
            </tr>
            <tr class="name-sublevel1-odd">
                <td>退出代码</td>
                <td>${stepExecutionInfo.stepExecution.exitStatus.exitCode}</td>
            </tr>
            <tr class="name-sublevel1-even">
                <td>退出信息</td>
                <td>${stepExecutionInfo.stepExecution.exitStatus.exitDescription!}</td>
            </tr>
        </table>
    
    <#else>
        <p>有没有作业执行显示。</p>
    </#if>
    
</div><!-- job-execution -->
