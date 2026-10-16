package com.mangzai.shapeshiftercompass.ui;

import com.mangzai.shapeshiftercompass.ai.Evidence;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import java.util.*;

/** Expandable local source cards. Opening or scrolling never calls an AI API. */
public final class EvidenceScreen extends CompassScreen {
    private final Screen parent;
    private final List<Evidence> evidence;
    private final Set<Integer> expanded=new HashSet<>();
    private final List<int[]> hitboxes=new ArrayList<>();
    private int scroll,maxScroll;
    public EvidenceScreen(Screen parent,List<Evidence> evidence) {
        super(Text.translatable("ssc_compass.chat.evidence")); this.parent=parent;
        this.evidence=evidence==null?List.of():List.copyOf(evidence);
        if(!this.evidence.isEmpty()) expanded.add(0);
    }
    @Override protected void init() {
        addDrawableChild(ButtonWidget.builder(Text.translatable("gui.back"),b->close()).dimensions(width/2-40,height-25,80,20).build());
    }
    @Override public void close() { client.setScreen(parent); }
    @Override public boolean mouseScrolled(double x,double y,double horizontalAmount,double amount) { scroll=Math.max(0,Math.min(maxScroll,scroll-(int)(amount*24))); return true; }
    @Override public boolean mouseClicked(double x,double y,int button) {
        if(button==0 && y>=30 && y<height-32) for(int[] box:hitboxes) if(x>=12 && x<=width-12 && y>=box[1] && y<box[2]) {
            if(!expanded.add(box[0])) expanded.remove(box[0]); return true;
        }
        return super.mouseClicked(x,y,button);
    }
    @Override public void render(DrawContext ctx,int mx,int my,float delta) {
        renderBackground(ctx, mx, my, delta); ctx.drawCenteredTextWithShadow(textRenderer,title,width/2,12,0xFFFFFFFF);
        hitboxes.clear(); int y=34-scroll;
        ctx.enableScissor(10,30,width-10,height-32);
        for(int i=0;i<evidence.size();i++) {
            Evidence card=evidence.get(i); int start=y;
            ctx.fill(12,y,width-12,y+19,0xE0364656);
            String header=(expanded.contains(i)?"▼ ":"▶ ")+card.title()+" · "+new java.text.SimpleDateFormat("HH:mm:ss").format(new Date(card.capturedAt()));
            ctx.drawTextWithShadow(textRenderer,Text.literal(textRenderer.trimToWidth(header,width-36)),18,y+5,0xFFFFFFFF);
            hitboxes.add(new int[]{i,start,start+19}); y+=22;
            if(expanded.contains(i)) {
                for(var line:textRenderer.wrapLines(Text.literal(card.basis()+"\n"+card.detail()),Math.max(40,width-44))) {
                    if(y>20 && y<height-30) ctx.drawTextWithShadow(textRenderer,line,20,y,0xFFDDDDDD);
                    y+=11;
                }
                y+=7;
            }
        }
        if(evidence.isEmpty()) ctx.drawTextWithShadow(textRenderer,Text.translatable("ssc_compass.chat.no_evidence"),20,38,0xFFBBBBBB);
        ctx.disableScissor(); maxScroll=Math.max(0,y+scroll-(height-35)); scroll=Math.min(scroll,maxScroll);
        super.render(ctx,mx,my,delta);
    }
}
