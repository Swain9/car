package cn.yuntangnet.duizhang.modules.business.service.impl;

import cn.yuntangnet.duizhang.modules.business.service.GuangXiBusiness;
import com.catt.datanet.service.bean.gxcloudpool.CloudPoolAclVmBizInfo;
import com.catt.datanet.service.bean.gxcloudpool.CloudPoolAggregationInterface;
import com.catt.datanet.service.bean.gxcloudpool.CloudPoolInterface;
import com.catt.datanet.service.bean.gxcloudpool.GxCloudPoolAggregationBizInfo;
import com.catt.datanet.service.bean.gxcloudpool.GxCloudPoolCloudAcBizInfo;
import com.catt.datanet.service.bean.gxcloudpool.GxCloudPoolCoreRouterBizInfo;
import com.catt.datanet.service.bean.gxcloudpool.GxCloudPoolFirewallBizInfo;
import com.catt.datanet.service.bean.gxcloudpool.GxCloudPoolIndustryApplicationBizInfo;
import com.catt.datanet.service.bean.gxcloudpool.GxCloudPoolVmBizInfo;
import com.catt.datanet.service.bean.gxcloudpool.GxCloudPoolZhuanXianAcBizInfo;
import com.catt.datanet.service.bean.script.VpnInstanceParam;
import com.catt.ipnet.confsrv.client.IpnetRestFactory;
import com.catt.ipnet.confsrv.client.api.GxCloudPoolZhuanXian;
import com.catt.ipnet.confsrv.common.bo.RestRequest;
import com.catt.ipnet.confsrv.common.bo.RestResponse;
import com.catt.ipnet.confsrv.common.bo.gxcloudpool.GxCloudPoolParam;
import com.catt.ipnet.confsrv.common.bo.gxcloudpool.GxCloudPoolResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <PRE>
 * 这里填写类注释
 * </PRE>
 *
 * @author zhangmaolin
 * @version 1.0.0
 * @since 2018-12-19 14:10
 */
@Service
public class GuangXiBusinessImpl implements GuangXiBusiness {

    @Autowired
    private IpnetRestFactory factory;

    @Override
    public RestResponse<GxCloudPoolResult> createGuangXiCloudPoolZhuanXian() {
        return guangXiCloudPoolZhuanXian(true, true);
    }

    @Override
    public RestResponse<GxCloudPoolResult> deleteGuangXiCloudPoolZhuanXian() {
        return guangXiCloudPoolZhuanXian(false, true);
    }

    /**
     * @param create  是创建还是删除
     * @param preview 是否预览模式，预览模式的时候，会返回脚本，但是不会下发到设备上面
     */
    private RestResponse<GxCloudPoolResult> guangXiCloudPoolZhuanXian(boolean create, boolean preview) {
        RestRequest<GxCloudPoolParam> request = new RestRequest<>();
        //业务ID
        request.setId("111");
        //业务名称
        request.setName("创建接口");
        //预览模式
        request.setPreview(preview);
        request.setLoginTest(preview);

        //云池业务参数
        GxCloudPoolParam param = getParam();
        request.setObj(param);
        GxCloudPoolZhuanXian gxCloudPoolZhuanXian = factory.createApi(GxCloudPoolZhuanXian.class);

        //是创建还是删除，调用不通接口
        if (create) {
            return gxCloudPoolZhuanXian.createGuangXiCloudPoolZhuanXian(request);

        } else {
            return gxCloudPoolZhuanXian.deleteGuangXiCloudPoolZhuanXian(request);
        }
    }

    private GxCloudPoolParam getParam() {
        GxCloudPoolParam param = new GxCloudPoolParam();
        param.setBizId("100");
        param.setBizName("guangxicloudpoolzhuanxian100");

        //行业应用交换机的信息
        GxCloudPoolIndustryApplicationBizInfo gxCloudPoolIndustryApplicationBizInfo = new GxCloudPoolIndustryApplicationBizInfo();
        param.setIndustryApplicationBizInfo(gxCloudPoolIndustryApplicationBizInfo);

        //专线接入交换机的信息
        GxCloudPoolZhuanXianAcBizInfo gxCloudPoolZhuanXianAcBizInfo = new GxCloudPoolZhuanXianAcBizInfo();
        param.setZhuanXinaAcBizInfo(gxCloudPoolZhuanXianAcBizInfo);

        //核心路由器的信息
        GxCloudPoolCoreRouterBizInfo gxCloudPoolCoreRouterBizInfo = new GxCloudPoolCoreRouterBizInfo();
        param.setCoreRouterBizInfo(gxCloudPoolCoreRouterBizInfo);

        //防火墙的信息
        GxCloudPoolFirewallBizInfo gxCloudPoolFirewallBizInfo = new GxCloudPoolFirewallBizInfo();
        param.setFirewallBizInfo(gxCloudPoolFirewallBizInfo);

        //汇聚交换机的信息
        GxCloudPoolAggregationBizInfo gxCloudPoolAggregationBizInfo = new GxCloudPoolAggregationBizInfo();
        param.setAggregationBizInfo(gxCloudPoolAggregationBizInfo);

        //云侧接入交换机的信息
        GxCloudPoolCloudAcBizInfo gxCloudPoolCloudAcBizInfo = new GxCloudPoolCloudAcBizInfo();
        param.setCloudAcBizInfo(gxCloudPoolCloudAcBizInfo);

        //虚拟机信息
        List<GxCloudPoolVmBizInfo> gxCloudPoolVmBizInfos = new ArrayList<>();
        param.setVmBizInfos(gxCloudPoolVmBizInfos);

        //行业应用交换机信息赋值
        addIndustryApplicationBizInfo(gxCloudPoolIndustryApplicationBizInfo);

        //专线接入交换机信息赋值
        addZhuanXianAcBizInfo(gxCloudPoolZhuanXianAcBizInfo);

        //核心路由器信息赋值
        addCoreRouterBizInfo(gxCloudPoolCoreRouterBizInfo);

        //防火墙信息赋值
        addFirewallBizInfo(gxCloudPoolFirewallBizInfo);

        //汇聚接入交换机信息赋值
        addAggregationBizInfo(gxCloudPoolAggregationBizInfo);

        //云侧接入交换机信息赋值
        addCloudAcBizInfo(gxCloudPoolCloudAcBizInfo);

        //虚拟机信息赋值
        addVmBizInfos(gxCloudPoolVmBizInfos);
        return param;
    }

    private void addFirewallBizInfo(GxCloudPoolFirewallBizInfo gxCloudPoolFirewallBizInfo) {
        String vlan = "129";
        String vpnInstanceName = vlan;
        gxCloudPoolFirewallBizInfo.setNeId("3");
        gxCloudPoolFirewallBizInfo.setVlan(vlan);
        gxCloudPoolFirewallBizInfo.setNeNameEn("firewall_sysname");

        //配置ACL VM绑定信息
        List<CloudPoolAclVmBizInfo> aclVmBizInfos = new ArrayList<CloudPoolAclVmBizInfo>();
        CloudPoolAclVmBizInfo cloudPoolAclVmBizInfo = new CloudPoolAclVmBizInfo();
        cloudPoolAclVmBizInfo.setAclId("3300");
        cloudPoolAclVmBizInfo.setVmIp("90.129.0.4");
        cloudPoolAclVmBizInfo.setPermitIp("172.0.0.0");
        cloudPoolAclVmBizInfo.setPermitIpMask("255.0.0.0");
        aclVmBizInfos.add(cloudPoolAclVmBizInfo);
        gxCloudPoolFirewallBizInfo.setAclVmBizInfos(aclVmBizInfos);

        //配置跟核心路由器连接的聚合口
        CloudPoolAggregationInterface toCoreRouterAggInter = new CloudPoolAggregationInterface();
        toCoreRouterAggInter.setPortId("1");
        toCoreRouterAggInter.setIp("172.23.0.50");
        toCoreRouterAggInter.setMask("255.255.255.0");
        toCoreRouterAggInter.setVlan("1299");
        gxCloudPoolFirewallBizInfo.setToCoreRouterAggInter(toCoreRouterAggInter);

        //配置跟核心路由器连接的物理口
        List<CloudPoolInterface> toCoreRouterInters = new ArrayList<>();
        CloudPoolInterface toCoreRouterInter = new CloudPoolInterface();
        toCoreRouterInter.setName("GigabitEthernet1/0/2");
        toCoreRouterInters.add(toCoreRouterInter);
        gxCloudPoolFirewallBizInfo.setToCoreRouterInters(toCoreRouterInters);

        //配置跟汇聚交换机连接的聚合口
        CloudPoolAggregationInterface toAggregationAggInter = new CloudPoolAggregationInterface();
        toAggregationAggInter.setPortId("2");
        toAggregationAggInter.setVlan("1295");
        toAggregationAggInter.setIp("172.22.2.6");
        toAggregationAggInter.setMask("255.255.255.252");

        gxCloudPoolFirewallBizInfo.setToAggregationAggInter(toAggregationAggInter);

        //配置跟汇聚交换机连接的物理口
        List<CloudPoolInterface> toAggregationInters = new ArrayList<>();
        CloudPoolInterface toAggregationInter = new CloudPoolInterface();
        toAggregationInter.setName("GigabitEthernet1/0/1");
        toAggregationInters.add(toAggregationInter);
        gxCloudPoolFirewallBizInfo.setToAggregationInters(toAggregationInters);
    }

    private void addVmBizInfos(List<GxCloudPoolVmBizInfo> gxCloudPoolVmBizInfos) {
        GxCloudPoolVmBizInfo vmBizInfo1 = new GxCloudPoolVmBizInfo();
        vmBizInfo1.setVmIp("90.129.0.4");
        vmBizInfo1.setVmIpNetwork("90.129.0.0");
        vmBizInfo1.setVmIpNetworkMask("255.255.255.0");
        vmBizInfo1.setVmNatIp("192.168.129.4");
        vmBizInfo1.setVmNatMask("255.255.255.0");

        gxCloudPoolVmBizInfos.add(vmBizInfo1);
    }

    private void addCloudAcBizInfo(GxCloudPoolCloudAcBizInfo gxCloudPoolCloudAcBizInfo) {
        String vlan = "129";
        String vpnInstanceName = vlan;
        gxCloudPoolCloudAcBizInfo.setNeId("5");
        gxCloudPoolCloudAcBizInfo.setVlan(vlan);
        gxCloudPoolCloudAcBizInfo.setNeNameEn("CloudAc_sysname");

        //配置跟汇聚交换机连接的聚合口
        CloudPoolAggregationInterface toAggregationAggInter = new CloudPoolAggregationInterface();
        toAggregationAggInter.setPortId("101");
        toAggregationAggInter.setVlan("1290");
        gxCloudPoolCloudAcBizInfo.setToAggregationAggInter(toAggregationAggInter);

        //配置跟汇聚交换机连接的物理口
        List<CloudPoolInterface> toAggregationInters = new ArrayList<>();
        CloudPoolInterface toAggregationInter1 = new CloudPoolInterface();
        toAggregationInter1.setName("GigabitEthernet1/0/1");
        toAggregationInters.add(toAggregationInter1);
        CloudPoolInterface toAggregationInter3 = new CloudPoolInterface();
        toAggregationInter3.setName("GigabitEthernet1/0/1");
        toAggregationInters.add(toAggregationInter3);
        gxCloudPoolCloudAcBizInfo.setToAggregationInters(toAggregationInters);

        //配置跟虚拟机相连的物理口

        CloudPoolInterface toVmInter = new CloudPoolInterface();
        toVmInter.setName("GigabitEthernet1/0/2");
        gxCloudPoolCloudAcBizInfo.setToVmInter(toVmInter);
    }

    private void addAggregationBizInfo(GxCloudPoolAggregationBizInfo gxCloudPoolAggregationBizInfo) {
        String vlan = "129";
        String vpnInstanceName = vlan;
        gxCloudPoolAggregationBizInfo.setNeId("4");
        gxCloudPoolAggregationBizInfo.setVlan(vlan);
        gxCloudPoolAggregationBizInfo.setNeNameEn("Aggregation_sysname");
        //VPN信息
        VpnInstanceParam vpnInstanceParam = new VpnInstanceParam();
        vpnInstanceParam.setVPN_INSTANCE_NAME(vpnInstanceName);
        vpnInstanceParam.setRD("65500:129");
        vpnInstanceParam.setRT_IN("65500:129");
        vpnInstanceParam.setRT_OUT("65500:129");
        gxCloudPoolAggregationBizInfo.setVpnInstanceParam(vpnInstanceParam);

        //创建跟防火墙连接的VLAN接口，绑定VPN，配置IP
        CloudPoolInterface toFirewallVlanInter = new CloudPoolInterface();
        toFirewallVlanInter.setVlan("1295");
        toFirewallVlanInter.setIp("172.22.2.5");
        toFirewallVlanInter.setMask("255.255.255.252");
        gxCloudPoolAggregationBizInfo.setToFirewallVlanInter(toFirewallVlanInter);

        //创建跟云侧接入交换机连接的VLAN接口，绑定VPN，配置IP
        CloudPoolInterface toCloudVlanInter = new CloudPoolInterface();
        toCloudVlanInter.setVlan("1290");
        toCloudVlanInter.setIp("90.129.0.254");
        toCloudVlanInter.setMask("255.255.255.0");
        gxCloudPoolAggregationBizInfo.setToCloudVlanInter(toCloudVlanInter);

        //创建跟防火墙互联的聚合口
        CloudPoolAggregationInterface toFirewallAggInter = new CloudPoolAggregationInterface();
        toFirewallAggInter.setPortId("103");
        toFirewallAggInter.setVlan("1295");
        gxCloudPoolAggregationBizInfo.setToFirewallAggInter(toFirewallAggInter);

        //创建跟防火墙互联的物理口
        List<CloudPoolInterface> toFirewallInters = new ArrayList<>();
        CloudPoolInterface toFirewallInter = new CloudPoolInterface();
        toFirewallInter.setName("GigabitEthernet1/0/2");
        toFirewallInters.add(toFirewallInter);
        gxCloudPoolAggregationBizInfo.setToFirewallInters(toFirewallInters);

        //创建跟云侧交换机互联的聚合口
        CloudPoolAggregationInterface toCloudAggInter = new CloudPoolAggregationInterface();
        toCloudAggInter.setPortId("26");
        toCloudAggInter.setVlan("1290");
        gxCloudPoolAggregationBizInfo.setToCloudAggInter(toCloudAggInter);

        //创建跟防火墙互联的物理口
        List<CloudPoolInterface> toCloudInters = new ArrayList<>();
        CloudPoolInterface toCloudInter = new CloudPoolInterface();
        toCloudInter.setName("GigabitEthernet1/0/1");
        toCloudInters.add(toCloudInter);
        gxCloudPoolAggregationBizInfo.setToCloudInters(toCloudInters);

    }

    private void addCoreRouterBizInfo(GxCloudPoolCoreRouterBizInfo gxCloudPoolCoreRouterBizInfo) {
        String vlan = "129";
        String vpnInstanceName = vlan;
        gxCloudPoolCoreRouterBizInfo.setNeId("2");
        gxCloudPoolCoreRouterBizInfo.setVlan(vlan);
        gxCloudPoolCoreRouterBizInfo.setNeNameEn("CoreRouter_sysname");

        //VPN信息
        VpnInstanceParam vpnInstanceParam = new VpnInstanceParam();
        vpnInstanceParam.setVPN_INSTANCE_NAME(vpnInstanceName);
        vpnInstanceParam.setRD("65500:129");
        vpnInstanceParam.setRT_IN("65500:129");
        vpnInstanceParam.setRT_OUT("65500:129");
        gxCloudPoolCoreRouterBizInfo.setVpnInstanceParam(vpnInstanceParam);

        //目前写死0.0.0.0
        //gxCloudPoolCoreRouterBizInfo.setto("0.0.0.0");

        //到专线接入交换机的聚合口
        CloudPoolAggregationInterface toZhuanXianAggInter = new CloudPoolAggregationInterface();
        toZhuanXianAggInter.setPortId("8");
        toZhuanXianAggInter.setVlan("129");
        toZhuanXianAggInter.setIp("192.168.129.1");
        toZhuanXianAggInter.setMask("255.255.255.252");
        gxCloudPoolCoreRouterBizInfo.setToZhuanXianAggInter(toZhuanXianAggInter);

        //到专线接入交换机的物理口
        List<CloudPoolInterface> toZhuanXianInters = new ArrayList<CloudPoolInterface>();

        CloudPoolInterface toZhuanXianInter = new CloudPoolInterface();
        toZhuanXianInter.setName("GigabitEthernet0/1");
        toZhuanXianInters.add(toZhuanXianInter);
        gxCloudPoolCoreRouterBizInfo.setToZhuanXianInters(toZhuanXianInters);

        //到防火墙的聚合口
        CloudPoolAggregationInterface toFirewallAggInter = new CloudPoolAggregationInterface();
        toFirewallAggInter.setPortId("1");
        toFirewallAggInter.setVlan("1299");
        toFirewallAggInter.setIp("172.23.0.49");
        toFirewallAggInter.setMask("255.255.255.0");
        gxCloudPoolCoreRouterBizInfo.setToFirewallAggInter(toFirewallAggInter);

        //到防火墙的物理口配置
        List<CloudPoolInterface> toFirewallInters = new ArrayList<CloudPoolInterface>();
        gxCloudPoolCoreRouterBizInfo.setToFirewallInters(toFirewallInters);
        CloudPoolInterface toZhuanXianInter1 = new CloudPoolInterface();
        toZhuanXianInter1.setName("GigabitEthernet0/0");
        toFirewallInters.add(toZhuanXianInter1);

    }

    private void addZhuanXianAcBizInfo(GxCloudPoolZhuanXianAcBizInfo gxCloudPoolZhuanXianAcBizInfo) {
        String vlan = "129";
        String vpnInstanceName = vlan;
        gxCloudPoolZhuanXianAcBizInfo.setNeId("1");
        gxCloudPoolZhuanXianAcBizInfo.setNeNameEn("zhuanxian_ac_sysname");
        gxCloudPoolZhuanXianAcBizInfo.setVlan(vlan);

        //到行业应用交换机的聚合口
        CloudPoolAggregationInterface cloudPoolAggregationInterface = new CloudPoolAggregationInterface();
        cloudPoolAggregationInterface.setPortId("10");
        cloudPoolAggregationInterface.setVlan("129");

        gxCloudPoolZhuanXianAcBizInfo.setToIndustryApplicationAggInter(cloudPoolAggregationInterface);
        //到行业应用交换机的物理口信息
        List<CloudPoolInterface> toIndustryApplicationInters = new ArrayList<CloudPoolInterface>();
        gxCloudPoolZhuanXianAcBizInfo.setToIndustryApplicationInters(toIndustryApplicationInters);
        //第一个端口
        CloudPoolInterface toIndustryApplicationInter1 = new CloudPoolInterface();
        toIndustryApplicationInter1.setName("GigabitEthernet1/0/2");
        toIndustryApplicationInters.add(toIndustryApplicationInter1);
        //第二个端口
        CloudPoolInterface toIndustryApplicationInter2 = new CloudPoolInterface();
        toIndustryApplicationInter2.setName("GigabitEthernet1/0/10");
        toIndustryApplicationInters.add(toIndustryApplicationInter2);

        //到核心路由器的端口信息
        CloudPoolInterface toCoreRouterInter = new CloudPoolInterface();
        toCoreRouterInter.setName("GigabitEthernet1/0/1");
        toCoreRouterInter.setVlan(vlan);
        gxCloudPoolZhuanXianAcBizInfo.setToCoreRouterInter(toCoreRouterInter);
    }

    private void addIndustryApplicationBizInfo(GxCloudPoolIndustryApplicationBizInfo gxCloudPoolIndustryApplicationBizInfo) {
        String vlan = "129";
        String vpnInstanceName = vlan;
        gxCloudPoolIndustryApplicationBizInfo.setVlan(vlan);
        gxCloudPoolIndustryApplicationBizInfo.setNeNameEn("IndustryApplication_sysname");
        CloudPoolInterface cloudPoolInterface = new CloudPoolInterface();
        cloudPoolInterface.setIp("192.168.129.2");
        cloudPoolInterface.setVlan(vlan);
        cloudPoolInterface.setMask("255.255.255.252");
        cloudPoolInterface.setName("GigabitEthernet1/0/1");
        gxCloudPoolIndustryApplicationBizInfo.setToZhuanXianInter(cloudPoolInterface);
    }
}
