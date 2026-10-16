package com.mangzai.shapeshiftercompass.tools;

import java.util.*;

/** Maximum flow prevents the same stack being counted twice across overlapping ingredients. */
public final class MaterialAllocator {
    public record Allocation(int[] missing, int[] used) {}
    private static final class Edge { int to, reverse, capacity; Edge(int t, int r, int c) { to=t; reverse=r; capacity=c; } }
    private MaterialAllocator() {}
    public static Allocation allocate(int[] available, int[] required, boolean[][] accepts) {
        int n=available.length, m=required.length, sink=n+m+1;
        List<List<Edge>> graph=new ArrayList<>();
        for(int i=0;i<=sink;i++) graph.add(new ArrayList<>());
        for(int i=0;i<n;i++) {
            add(graph,0,i+1,Math.max(0,available[i]));
            for(int j=0;j<m;j++) if(accepts[i][j]) add(graph,i+1,n+j+1,Math.max(0,required[j]));
        }
        Edge[] demands=new Edge[m];
        for(int j=0;j<m;j++) demands[j]=add(graph,n+j+1,sink,Math.max(0,required[j]));
        while(true) {
            int[] parent=new int[sink+1], index=new int[sink+1]; Arrays.fill(parent,-1);
            var queue=new ArrayDeque<Integer>(); queue.add(0); parent[0]=0;
            while(!queue.isEmpty() && parent[sink]<0) {
                int u=queue.remove();
                for(int k=0;k<graph.get(u).size();k++) { Edge e=graph.get(u).get(k);
                    if(e.capacity>0 && parent[e.to]<0) { parent[e.to]=u; index[e.to]=k; queue.add(e.to); }
                }
            }
            if(parent[sink]<0) break;
            int flow=Integer.MAX_VALUE;
            for(int v=sink;v!=0;v=parent[v]) flow=Math.min(flow,graph.get(parent[v]).get(index[v]).capacity);
            for(int v=sink;v!=0;v=parent[v]) { Edge e=graph.get(parent[v]).get(index[v]); e.capacity-=flow; graph.get(v).get(e.reverse).capacity+=flow; }
        }
        int[] missing=new int[m], used=new int[n];
        for(int j=0;j<m;j++) missing[j]=demands[j].capacity;
        for(int i=0;i<n;i++) used[i]=Math.max(0,available[i])-graph.get(0).get(i).capacity;
        return new Allocation(missing,used);
    }
    private static Edge add(List<List<Edge>> g,int u,int v,int amount) {
        Edge forward=new Edge(v,g.get(v).size(),amount), reverse=new Edge(u,g.get(u).size(),0);
        g.get(u).add(forward); g.get(v).add(reverse); return forward;
    }
}
