package otokatari.com.otokatari.InfrastructureExtension.TasksExtensions;

public interface TaskPostExecuteWrapper<TTaskReturn>
{
    void DoOnPostExecute(TTaskReturn TaskRet);
}
