/**
 * Title: StepController.java
 * Description:
 * Copyright: Copyright (c) 2015
 * Company: gigold
 */
package org.springframework.batch.admin.web;

import java.util.TimeZone;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.batch.admin.history.StepExecutionHistory;
import org.springframework.batch.admin.service.JobService;
import org.springframework.batch.admin.service.NoSuchStepExecutionException;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.SimpleStepHandler;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.AbstractStep;
import org.springframework.batch.core.step.job.JobStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Title: StepController Description: Company: gigold
 * 
 * @author liubao
 * @date 2015年12月21日下午8:22:31
 */
@Controller
public class StepController
{
    public static class StopRequest 
    {
        private Long jobExecutionId;
        
        private Long stepExecutionId;
        
        public Long getStepExecutionId()
        {
            return stepExecutionId;
        }

        public void setStepExecutionId(Long stepExecutionId)
        {
            this.stepExecutionId = stepExecutionId;
        }

        public Long getJobExecutionId() 
        {
            return jobExecutionId;
        }

        public void setJobExecutionId(Long jobExecutionId) 
        {
            this.jobExecutionId = jobExecutionId;
        }

    }
    
    private JobService jobService;
    private TimeZone timeZone = TimeZone.getDefault();
    private ObjectMapper objectMapper;

    /**
     * @param timeZone
     *            the timeZone to set
     */
    @Autowired(required = false)
    @Qualifier("userTimeZone")
    public void setTimeZone(TimeZone timeZone)
    {
        this.timeZone = timeZone;
    }

    @Autowired
    public StepController(JobService jobService) 
    {
        super();
        this.jobService = jobService;
    }
    
    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) 
    {
        this.objectMapper = objectMapper;
    }
    
    @Autowired
    private JobRepository jobRepository;
    
    /**
     * Title: stepStop
     * Description:停止所有执行步
     * @author liubao
     * @date 2015年12月23日上午9:53:38
     * @param model
     * @param stopRequest
     * @param errors
     * @param jobExecutionId
     * @param stepExecutionId
     * @return
     */
    @RequestMapping(value = "/jobs/executions/{jobExecutionId}/steps/{stepExecutionId}/stop", method = RequestMethod.DELETE)
    public String stepStop(Model model, @ModelAttribute("stopRequest") StopRequest stopRequest,
            Errors errors,@PathVariable Long jobExecutionId,@PathVariable Long stepExecutionId) 
    {
        stopRequest.jobExecutionId = jobExecutionId;
        stopRequest.jobExecutionId = stepExecutionId;
        try 
        {
            JobExecution jobExecution = jobService.stop(jobExecutionId);
            StepExecution stepExecution = jobService.getStepExecution(jobExecutionId,stepExecutionId);
            model.addAttribute(new StepExecutionInfo(stepExecution, timeZone));
            String stepName = stepExecution.getStepName();
            if (stepName.contains(":partition"))
            {
                stepName = stepName.replaceAll("(:partition).*", "$1*");
            }
            String jobName = stepExecution.getJobExecution().getJobInstance().getJobName();
            StepExecutionHistory stepExecutionHistory = computeHistory(jobName, stepName);
            model.addAttribute(stepExecutionHistory);
            model.addAttribute(new StepExecutionProgress(stepExecution, stepExecutionHistory));
        }
        catch(Exception e)
        {
            errors.reject("no.such.step.execution", new Object[] { jobExecutionId }, "执行失败("+ jobExecutionId + ")");
            try
            {
                StepExecution stepExecution = jobService.getStepExecution(jobExecutionId,stepExecutionId);
                model.addAttribute(new StepExecutionInfo(stepExecution, timeZone));
                String stepName = stepExecution.getStepName();
                if (stepName.contains(":partition"))
                {
                    stepName = stepName.replaceAll("(:partition).*", "$1*");
                }
                String jobName = stepExecution.getJobExecution().getJobInstance().getJobName();
                StepExecutionHistory stepExecutionHistory = computeHistory(jobName, stepName);
                model.addAttribute(stepExecutionHistory);
                model.addAttribute(new StepExecutionProgress(stepExecution, stepExecutionHistory));
            }
            catch (NoSuchStepExecutionException e1)
            {
                e1.printStackTrace();
            } 
            catch (NoSuchJobExecutionException e1)
            {
                e1.printStackTrace();
            }
        }

        return "jobs/executions/step/progress";
    }
    
    /**
     * Title: stepRestart
     * Description:重启执行步
     * @author liubao
     * @date 2015年12月23日上午9:57:21
     * @param model
     * @param stopRequest
     * @param errors
     * @param jobExecutionId
     * @param stepExecutionId
     * @return
     */
    @RequestMapping(value = "/jobs/executions/{jobExecutionId}/steps/{stepExecutionName}/{stepExecutionId}/restart", method = RequestMethod.POST)
    public String stepRestart(Model model, @ModelAttribute("stopRequest") StopRequest stopRequest,
            Errors errors,@PathVariable Long jobExecutionId,@PathVariable String stepExecutionName,@PathVariable Long stepExecutionId) 
    {
        try
        {
            SimpleStepHandler stepHandler = new SimpleStepHandler(jobRepository, jobService.getJobExecution(jobExecutionId).getExecutionContext());
            AbstractStep step = new JobStep();
            step.setName(stepExecutionName);
            step.setJobRepository(jobRepository);
            stepHandler.handleStep(step, jobService.getJobExecution(jobExecutionId));
            
            StepExecution stepExecution = jobService.getStepExecution(jobExecutionId,stepExecutionId);
            model.addAttribute(new StepExecutionInfo(stepExecution, timeZone));
            String stepName = stepExecution.getStepName();
            if (stepName.contains(":partition"))
            {
                stepName = stepName.replaceAll("(:partition).*", "$1*");
            }
            String jobName = stepExecution.getJobExecution().getJobInstance().getJobName();
            StepExecutionHistory stepExecutionHistory = computeHistory(jobName, stepName);
            model.addAttribute(stepExecutionHistory);
            model.addAttribute(new StepExecutionProgress(stepExecution, stepExecutionHistory));
        } 
        catch (Exception e)
        {
            errors.reject("no.such.step.execution", new Object[] { jobExecutionId }, "执行失败("+ jobExecutionId + ")");
            try
            {
                StepExecution stepExecution = jobService.getStepExecution(jobExecutionId,stepExecutionId);
                model.addAttribute(new StepExecutionInfo(stepExecution, timeZone));
                String stepName = stepExecution.getStepName();
                if (stepName.contains(":partition"))
                {
                    stepName = stepName.replaceAll("(:partition).*", "$1*");
                }
                String jobName = stepExecution.getJobExecution().getJobInstance().getJobName();
                StepExecutionHistory stepExecutionHistory = computeHistory(jobName, stepName);
                model.addAttribute(stepExecutionHistory);
                model.addAttribute(new StepExecutionProgress(stepExecution, stepExecutionHistory));
            }
            catch (Exception e1)
            {
                
            } 
        } 
        
        return "jobs/executions/step/progress";
    }
    
    private StepExecutionHistory computeHistory(String jobName, String stepName) 
    {
        int total = jobService.countStepExecutionsForStep(jobName, stepName);
        StepExecutionHistory stepExecutionHistory = new StepExecutionHistory(stepName);
        for (int i = 0; i < total; i += 1000) 
        {
            for (StepExecution stepExecution : jobService.listStepExecutionsForStep(jobName, stepName, i, 1000)) 
            {
                stepExecutionHistory.append(stepExecution);
            }
        }
        return stepExecutionHistory;
    }
}
